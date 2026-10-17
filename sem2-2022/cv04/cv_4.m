A=[0, 1, 1, 1, 0]';
B=[1, 0, 1, 0, 0]';
C=[1, 1, 0, 1, 1]';
D=[1, 0, 0, 0, 0]';
E=[1, 0, 1, 1, 0]';
M=[A B C D E];
webs = ['A', 'B', 'C', 'D', 'E'];
% "initial guess" - jednotkovej vektor
y0=ones(size(M,1),1);
% pro kazdy vektor v M - pro kazdou webovou stranku
% prevede matici M na levou stochastickou matici (soucty hodnot ve sloupcich == 1)
for j=1:size(M)
    % n = pocet outgoing linku na dane webove strance
    n=(sum(M(:,j)==1));
    % kady prvek vydeli n
    M(:,j)=M(:,j)/n;
end
% najde nejvetsi vlastni cislo a jeho vektor
[vec,lambda]=mocninna_metoda(M,y0,1e-8,100)
% poprehazet chars v matici webs a vyprintit
[B,I]=sort(vec,"ascend");
webs=webs(I)