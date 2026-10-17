% graf pro data0
% data
fileName = sprintf('data0.mat');
data = load(fileName, '-ascii');
x = data(:,1);
y = data(:,2);
xq=min(x):0.04:max(x);

% jednotlivy typy interpolaci
yq = lagrange(x,y,xq);
yq1 = interp1(x,y,xq,'linear');
yq2 = spline(x,y,xq);
yq3 = pchip(x,y,xq);
yq4 = makima(x,y,xq);

% vykreslit vsechny grafy do jedny figury
figure(1)
plot(yq)
hold on
plot(yq1)
hold on
plot(yq2)
hold on
plot(yq3)
hold on
plot(yq4)

% popisky
xlabel('x')
ylabel('f(x)')
legend('lagrange','linear','spline','pchip','makima')
% ulozit file
saveas(gcf,'graph_data0.png')

% graf pro data1
fileName = sprintf('data1.mat');
data = load(fileName, '-ascii');
x = data(:,1);
y = data(:,2);
xq=min(x):0.08:max(x);
yq = lagrange(x,y,xq);
yq1 = interp1(x,y,xq,'linear');
yq2 = spline(x,y,xq);
yq3 = pchip(x,y,xq);
yq4 = makima(x,y,xq);
figure(2)
plot(yq)
hold on
plot(yq1)
hold on
plot(yq2)
hold on
plot(yq3)
hold on
plot(yq4)
xlabel('x')
ylabel('f(x)')
legend('lagrange','linear','spline','pchip','makima')
saveas(gcf,'graph_data1.png')

% graf pro data2
fileName = sprintf('data2.mat');
data = load(fileName, '-ascii');
x = data(:,1);
y = data(:,2);
xq=min(x):0.05:max(x);
yq1 = interp1(x,y,xq,'linear');
yq2 = spline(x,y,xq);
yq3 = pchip(x,y,xq);
yq4 = makima(x,y,xq);
figure(3)
plot(yq)
hold on
plot(yq1)
hold on
plot(yq2)
hold on
plot(yq3)
hold on
plot(yq4)
xlabel('x')
ylabel('f(x)')
legend('linear','spline','pchip','makima')
saveas(gcf,'graph_data2.png')

% graf pro data3
fileName = sprintf('data3.mat');
data = load(fileName, '-ascii');
x = data(:,1);
y = data(:,2);
xq=min(x):0.04:max(x);
yq1 = interp1(x,y,xq,'linear');
yq2 = spline(x,y,xq);
yq3 = pchip(x,y,xq);
yq4 = makima(x,y,xq);
figure(4)
plot(yq)
hold on
plot(yq1)
hold on
plot(yq2)
hold on
plot(yq3)
hold on
plot(yq4)
xlabel('x')
ylabel('f(x)')
legend('linear','spline','pchip','makima')
saveas(gcf,'graph_data3.png')