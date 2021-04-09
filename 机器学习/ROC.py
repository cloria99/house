import matplotlib.pyplot as plt
import numpy as np
data=open(r'C:\Users\Lenovo\Desktop\dataset_for_pr_roc.txt\dataset_for_pr_roc.txt')
dataset=[]
for i in range(1000):
    datal=data.readline()
    datal=datal.split()
    datal[0]=int(datal[0])
    datal[1]=float(datal[1])
    dataset.append(datal)
    
dataset.sort(key=lambda dataset:dataset[1],reverse=True)
ROC=[]
for i in range(1000):
    TP=0
    FN=0
    FP=0
    TN=0
    i=i+1
    for j in range(i):
        if dataset[j][0]==1:
            TP=TP+1
        if dataset[j][0]==0:
            FP=FP+1
    for j in range(i,1000):
        if dataset[j][0]==1:
            FN=FN+1
        if dataset[j][0]==0:
            TN=TN+1
    ROC.append([TP,FN,FP,TN])
TPR=[]
FPR=[]
for i in range(1000):
    TPR.append(ROC[i][0]/(ROC[i][0]+ROC[i][1]))
    FPR.append(ROC[i][2]/(ROC[i][3]+ROC[i][2]))
    #print(TPR)
    #print(FPR)
data.close()
plt.figure()
plt.title('ROC')
plt.plot(FPR,TPR)
plt.xlabel('False Positive Rate')
plt.ylabel('True positive Rate')
plt.show()
