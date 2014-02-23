import re
import csv
import pprint
import nltk.classify
import MySQLdb as mdb
import sys
import time
import pickle

#Load emoticons from file
fp = open('emoticons.txt', "r")
emoticons = {}
for line in fp:
	row = line.rstrip().split(',')
        emoticons[str(row[0])] = row[1]

fp.close() 

#replace repeated characters
def replaceRepeatedLetters(s):
    #look for 2 or more repetitions of character
    pattern = re.compile(r"(.)\1{1,}", re.DOTALL) 
    return pattern.sub(r"\1\1", s)
#end

#load  StopWords from given file
def readStopWordList(stopWordFile):
    stopWords = []
    
    fp = open(stopWordFile, 'r')
    line = fp.readline()
    while line:
        word = line.strip()
        stopWords.append(word)
        line = fp.readline()

    stopWords.append('URL')	
    stopWords.append('AT_USER')	
    fp.close()
    return stopWords
#end

#start process_tweet
def processRawTweet(tweet):
    # process the tweets
    
    #lower case conversion
    tweet = tweet.lower()
    #Replace www.* or https?://* by URL
    tweet = re.sub('((www\.[\s]+)|(https?://[^\s]+))','URL',tweet)
    #Replace @username by AT_USER
    tweet = re.sub('@[^\s]+','AT_USER',tweet)    
    #strip extra white spaces
    tweet = re.sub('[\s]+', ' ', tweet)
    #Convert #word to word
    tweet = re.sub(r'#([^\s]+)', r'\1', tweet)
    
    tweet = tweet.strip('\'"')
    return tweet
#end 



#start getFeaturesInVector
def getFeaturesInVector(tweet, stopWords):
    FeatureVectoryContainer = []
    
    words = tweet.split()
    for w in words:
        #replace two or more with two occurrences 
        w = replaceRepeatedLetters(w) 
        #strip punctuation
        w = w.strip('\'"?,.')
        #check if it consists of only words
        val = re.search(r"^[a-zA-Z][a-zA-Z0-9]*[a-zA-Z]+[a-zA-Z0-9]*$", w)
	
	#replace emoticons
	word_to_replace= emoticons.get(w)
	if (word_to_replace is not None):
		w = word_to_replace
	
        #ignore if it is a stopWord
        if(w in stopWords or val is None):
            continue
        else:
            FeatureVectoryContainer.append(w.lower())
    return FeatureVectoryContainer    
#end

#start getTotalFeatureList
def getTotalFeatureList(fileName):
    fp = open(fileName, 'r')
    line = fp.readline()
    featureListContainer = []
    while line:
        line = line.strip()
        featureListContainer.append(line)
        line = fp.readline()
    fp.close()
    return featureListContainer
#end

#Feature set  extraction from tweet
def extractFeaturesFromTweet(tweet):
    tweet_words = set(tweet)
    features = {}
    for word in featureListContainer:
        features['contains(%s)' % word] = (word in tweet_words)
    return features
#end


#Read the tweets one by one and process it
input_Tweets = csv.reader(open('data/feature_list/full_training_dataset.csv', 'rb'), delimiter=',', quotechar='|')
st = open('data/feature_list/stopwords.txt', 'r')
stopWords = readStopWordList('data/feature_list/stopwords.txt')
featureListContainer = getTotalFeatureList('data/feature_list/feature_list.txt')
pp = pprint.PrettyPrinter()
count = 0;
tweets = []
for row in input_Tweets:
    sentiment = row[0]
    tweet = row[1]
    processedTweet = processRawTweet(tweet)
    FeatureVectoryContainer = getFeaturesInVector(processedTweet, stopWords)
    tweets.append((FeatureVectoryContainer, sentiment));
#end loop

#training_set = nltk.classify.util.apply_features(extractFeaturesFromTweet, tweets)
#pp.pprint(training_set)

# Train and save the Naive Bayes classifier
#NBClassifier = nltk.NaiveBayesClassifier.train(training_set)
#fc = open('my_classifier.pickle', 'wb')
#pickle.dump(NBClassifier, fc)
#fc.close()

# Load the Naive Bayes classifier
f= open('my_classifier.pickle')
classifier = pickle.load(f)
f.close()

#Infinite loop to read and process tweets
var = 1
while var == 1 :
	#Connect to database and fetch tweets:
	conn = None

	try:
		conn = mdb.connect(host="localhost",
					user="root",
					passwd="nazya",
					db="mydb")

		cursor = conn.cursor()
		query = "Select id, TweetText, Location from tweets where status='u'"
		cursor.execute(query)
		rows = cursor.fetchall()

		for row in rows:
			newTweet = row[1]
			t_id = row[0]
			processedNewTweet = processRawTweet(newTweet)
			sentiment = "neutral"
			#Using trained classifier to extract sentiment
			sentiment = classifier.classify(extractFeaturesFromTweet(getFeaturesInVector(processedNewTweet, stopWords)))
			print "newTweet = %s, sentiment = %s\n" % (newTweet, sentiment)
		        #updateQuery="update tweets set status = 'p', sentiment= where TweetText != 'effective but too-tepid biopic';"
			cursor.execute("UPDATE tweets SET Sentiment = %s, status = %s WHERE id = %s", 
				( sentiment, 'p', t_id))
			conn.commit()        
	    
			print "Number of rows updated: %d" % cursor.rowcount
	
		
	except mdb.Error, e:
		print "Error %d: %s" % (e.args[0],e.args[1])
		sys.exit(1)
	    
	finally:    
		
	    if conn:    
		cursor.close()
		conn.close()
		print "Sleeping of 100 seconds!!!"
		time.sleep(100)

	#end db
else :
	print "Good Bye!!!"

