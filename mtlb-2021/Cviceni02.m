% 1)
matrixC = randn(floor(10 + (30)*rand(1,3)));

% 2)
% a)
sumC = 0;
for row = 1:size(matrixC, 1)
    for column = 1:size(matrixC, 2)
        for page = 1:size(matrixC, 3)
            sumC = sumC + matrixC(row, column, page);
        end
    end
end
 
averageFor = sumC / numel(matrixC);

% b)
averageSum = sum(matrixC, 'all')/numel(matrixC);

% 3)
squeezedC = squeeze(matrixC(1,:,:));

for row = 1:size(squeezedC, 1)
    suma = sum(squeezedC(row,:));
    len = size(squeezedC(row, :),2);
end

% 4)
my_function(input('Zadej hodnotu x: '))

function y = my_function(x)
    y = ((sin(x))^2)*cos(x);
end
