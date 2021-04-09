'''
Created on Sep 16, 2010
kNN: k Nearest Neighbors

Input:      inX: vector to compare to existing dataset (1xN)
            dataSet: size m data set of known vectors (NxM)
            labels: data set labels (1xM vector)
            k: number of neighbors to use for comparison (should be an odd number)
            
Output:     the most popular class label

@author: pbharrin
'''
from numpy import *
import operator
from os import listdir

def classify0(inX, dataSet, labels, k):#
    dataSetSize = dataSet.shape[0]# shape[0] stands for the num of row  
    ## step 1: calculate Euclidean distance  
    # tile(A, reps): Construct an array by repeating A reps times  
    # the following copy numSamples rows for dataSet  
    diffMat = tile(inX, (dataSetSize,1)) - dataSet# Subtract element-wise分类向量与各个样本向量的差
    sqDiffMat = diffMat**2# squared for the subtract  矩阵中的每个元素的平方
    sqDistances = sqDiffMat.sum(axis=1)# sum is performed by row 矩阵按行将每个元素相加,得到一个向量
    distances = sqDistances**0.5#元素开方,得到一个向量
     ## step 2: sort the distance  
    # argsort() returns the indices that would sort an array in a ascending order
    sortedDistIndicies = distances.argsort()   #对向量从小到大排序，使用的是索引值,得到一个向量  
    classCount={} # define a dictionary (can be append element)           
    for i in range(k):
        ## step 3: choose the min k distance 
        voteIlabel = labels[sortedDistIndicies[i]]
        ## step 4: count the times labels occur  
        # when the key voteLabel is not in dictionary classCount, get()  
        # will return 0  
        classCount[voteIlabel] = classCount.get(voteIlabel,0) + 1
         ## step 5: the max voted class will return  
    sortedClassCount = sorted(classCount.items(),key=lambda x:x[1],reverse=True)
    return sortedClassCount[0][0]

def createDataSet():
    group = array([[1.0,1.1],[1.0,1.0],[0,0],[0,0.1]])
    labels = ['A','A','B','B']
    return group, labels

def file2matrix(filename):
    fr = open(filename)
    numberOfLines = len(fr.readlines())         #get the number of lines in the file
    returnMat = zeros((numberOfLines,3))        #prepare matrix to return
    classLabelVector = []                       #prepare labels return   
    fr = open(filename)
    index = 0
    for line in fr.readlines():
        line = line.strip()
        listFromLine = line.split('\t')
        returnMat[index,:] = listFromLine[0:3]
        classLabelVector.append(int(listFromLine[-1]))
        index += 1
    return returnMat,classLabelVector
    
def autoNorm(dataSet):
    minVals = dataSet.min(0)
    maxVals = dataSet.max(0)
    ranges = maxVals - minVals
    normDataSet = zeros(shape(dataSet))
    m = dataSet.shape[0]
    normDataSet = dataSet - tile(minVals, (m,1))
    normDataSet = normDataSet/tile(ranges, (m,1))   #element wise divide
    return normDataSet, ranges, minVals
   
def datingClassTest():
    hoRatio = 0.50      #hold out 10%
    datingDataMat,datingLabels = file2matrix('datingTestSet2.txt')       #load data setfrom file
    normMat, ranges, minVals = autoNorm(datingDataMat)
    m = normMat.shape[0]
    numTestVecs = int(m*hoRatio)
    errorCount = 0.0
    for i in range(numTestVecs):
        classifierResult = classify0(normMat[i,:],normMat[numTestVecs:m,:],datingLabels[numTestVecs:m],3)
        print("the classifier came back with: %d, the real answer is: %d" % (classifierResult, datingLabels[i]))
        if (classifierResult != datingLabels[i]): errorCount += 1.0
    print ("the total error rate is: %f" % (errorCount/float(numTestVecs)))
    print (errorCount)
    
def img2vector(filename):#convert image to vector 
    returnVect = zeros((1,1024))##返回1*1024行向量 
    fr = open(filename)
    for i in range(32):
        #一次只读一行 
        lineStr = fr.readline()
        for j in range(32):
            #行复制 
            returnVect[0,32*i+j] = int(lineStr[j])
    return returnVect

def handwritingClassTest():
    ##对测试集中的每条记录使用分类算法计算其分类 
    #分别计算这条记录与所有训练集数据的欧氏距离 
    #从所有距离中选出距离最小的K条数据 
    #将这K条数据对应的类别放入一个字典集中，并降序排列 
    #字典集中的第一个key/value对的key就是这条测试数据的分类
    hwLabels = []
    # listdir 可以列出trainingDigits文件夹目录中的文件
    testDir='testDigits' #iterate through the test set 
    trainDir='trainingDigits'  #check the len of trainingFileList 
    trainingFileList = listdir(trainDir)           #load the training set
    m = len(trainingFileList)    #每行数据存储一个图像 
    trainingMat = zeros((m,1024))
    for i in range(m): #get one name of trainingFileList，ex:0_17.txt  
        fileNameStr = trainingFileList[i] #get"0_17"; 
        fileStr = fileNameStr.split('.')[0]     #take off .txt
        #split函数，去除'.'，然后将剩余两侧元素分为一行二列的 
        #向量，然后[0]得到第一列，即0_17  
        #get"0"  
        classNumStr = int(fileStr.split('_')[0])
        hwLabels.append(classNumStr)
        trainingMat[i,:] = img2vector('%s/%s' % (trainDir,fileNameStr))
    testFileList = listdir(testDir)        #iterate through the test set
    errorCount = 0.0
    mTest = len(testFileList)
    for i in range(mTest):#以下为对测试数据的相同处理  
        fileNameStr = testFileList[i]
        fileStr = fileNameStr.split('.')[0]     #take off .txt
        classNumStr = int(fileStr.split('_')[0])
        vectorUnderTest = img2vector('%s/%s' % (testDir,fileNameStr))
         #k近邻算法 
        classifierResult = classify0(vectorUnderTest, trainingMat, hwLabels, 5)
        #算法输出与结果比较  
        print("the classifier came back with: %d, the real answer is: %d" % (classifierResult, classNumStr))
        if (classifierResult != classNumStr): errorCount += 1.0
    print ("\nthe total test number is: %d, errors is: %d"  % (mTest,errorCount))
    print ("\nthe total error rate is: %f %%" % (errorCount/float(mTest)*100))
    print ("\nthe total correct rate is: %f %%" % ((1.0-errorCount/float(mTest))*100))
