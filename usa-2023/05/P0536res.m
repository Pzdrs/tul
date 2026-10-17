clear all
clc

pravd=logncdf(4,3,4)-logncdf(2,3,4)

x=0:0.001:5;
y=lognpdf(x,3,4);
plot(x,y)