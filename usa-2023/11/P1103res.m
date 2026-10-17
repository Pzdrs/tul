clear all
clc

vstup=importdata('P1103.xlsx');
x=vstup.data.List1(:,1);
y=vstup.data.List1(:,2);
plot(x,y,'x')
LM=LinearModel.fit(x,y)
