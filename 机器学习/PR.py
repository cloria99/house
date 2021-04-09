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
PR=[]
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
    PR.append([TP,FN,FP,TN])
P=[]
R=[]
for i in range(1000):
    P.append(PR[i][0]/(PR[i][0]+PR[i][2]))
    R.append(PR[i][0]/(PR[i][0]+PR[i][1]))
    #print(P)
    #print(R)
data.close()
plt.figure()
plt.title('PR')
plt.plot(R,P)
plt.xlabel("Recall")
plt.ylabel("Precison")
plt.show()
