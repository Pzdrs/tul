clear all
clc

%vygeneruji náhodná èísla z jednic z rovnomìrného rozdìlení
A=rand(10000,1);    
subplot(2,2,1)
hist(A,100)

%vygeneruji náhodná èísla z dvojic z rovnomìrného rozdìlení
B=rand(10000,2);
for i=1:10000
    B_new(i)=(B(i,1)+B(i,2))/2;
end
subplot(2,2,2)
hist(B_new,100)

%vygeneruji náhodná èísla z dvojic z rovnomìrného rozdìlení
B=rand(10000,5);
for i=1:10000
    B_new(i)=sum(B(i,:))/5;
end
subplot(2,2,3)
hist(B_new,100)

%vygeneruji náhodná èísla z desetic z rovnomìrného rozdìlení
B=rand(10000,10);
for i=1:10000
    B_new(i)=sum(B(i,:))/10;
end
subplot(2,2,4)
hist(B_new,100)

