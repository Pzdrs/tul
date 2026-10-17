clear all
clc

x=[2,5,8,11,5,10,6];
y=[6,11,15,22,25,27,30];
plot(x,y,'x');

LM=LinearModel.fit(x,y)