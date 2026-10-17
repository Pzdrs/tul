clear all
clc

vstup=importdata('P1122.csv');
x=vstup(:,1);
y=vstup(:,2);


modelfun=@(b,x)(b(1)./(x.^b(2)));
beta0=[0.08,1]
NLM=NonLinearModel.fit(x,y,modelfun,beta0)

%například počáteční řešení [10,-1], nemá vliv na výsledky
modelfun=@(b,x)(b(1)./(x.^b(2)));
beta0=[10,-1]
NLM=NonLinearModel.fit(x,y,modelfun,beta0)
%například počáteční řešení [0,0], nemá vliv na výsledky
modelfun=@(b,x)(b(1)./(x.^b(2)));
beta0=[0,0]
NLM=NonLinearModel.fit(x,y,modelfun,beta0)
