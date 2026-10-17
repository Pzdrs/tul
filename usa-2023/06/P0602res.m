clear all
clc

%vygeneruji náhodná èísla z desetic z rovnomìrného rozdìlení
B=rand(1000000,10);
for i=1:1000000
    B_new(i)=sum(B(i,:));
end

x=0:0.01:10;
fx=8000*normpdf(x,5,sqrt(10/12));
hold on
hist(B_new,1000)
plot(x,fx,'r')


hold off