clear all
clc

vstup=importdata('P1113.xlsx');
x=vstup.data.List1(:,1);
y=vstup.data.List1(:,2);

%ad a)
modelfun=@(b,x)(b(1).*sin(b(2).*x+b(3)));
beta0=[5,1/3,1]
NLM=fitnlm(x,y,modelfun,beta0)

%ad b)
modelfun=@(b,x)(b(1).*sin(b(2).*x+b(3)));
beta0=[1,1,0]
NLM=fitnlm(x,y,modelfun,beta0)

%ad c)
hold on
plot(x,y,'x')
x=0:0.1:20;
plot(x,4.9581*sin(0.34398*x+0.89692))
plot(x,1.1501*sin(0.8986*x-0.38972))
hold off