clear all
clc

x=[3,5,8,11,12,14,15];
y=[6,11,15,22,25,27,30];

plot(x,y,'x')
LM=fitlm(x,y)
