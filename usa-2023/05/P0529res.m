clear all
clc

%ad a
x=exprnd(100,1,1000);
%ad b
y=wblrnd(100,1.5,1,1000);
%ad c
z=wblrnd(100,3,1,1000);
%ad d
a=normrnd(100,30,1,1000);

hold on
subplot(3,2,1);
boxplot(x)
subplot(3,2,2);
boxplot(y)
subplot(3,2,3);
boxplot(z)
subplot(3,2,4);
boxplot(a)

%ad e
data=[x,y,z,a];
sloupec1=ones(1,1000);
sloupec2=2*ones(1,1000);
sloupec3=3*ones(1,1000);
sloupec4=4*ones(1,1000);
sloupec=[sloupec1,sloupec2,sloupec3,sloupec4];
subplot(3,2,5:6)
boxplot(data,sloupec)
hold off