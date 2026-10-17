%{
    OMLOUVAM SE ZA MOU ABSENCI NA CVICENICH 7.10. - ZASPAL JSEM :(
%}

% 1)
vectorA = 1:50;

% 2)
vectorB = 1:0.2:10;

% 3)
matrixA = randi(9,3);
matrixB = zeros(3);
matrixD = ones(3);

% 4)
matrixM = [matrixA matrixB matrixD];

% 5)
matrixC = [1 -1 1; 1 -1 0; -1 0 1];

% 6)
matrixB1 = inv(matrixC);

% 7)
    % a.
    matrixC .* matrixB1;
    % b.
    matrixC * matrixB1;

% 8)
matrixM' * matrixC;

% 9) pravdepodobne existuje lepsi reseni, ale zatim o nem nevim
for row = 1:3
    matrixC(row,2) = randi(10);
end

% 10)
matrixC(1,:) = [4 2 0];

% 11) 
matrixC1 = matrixC(1, [2 3]);

% 12) uvazoval jsem -> priradit == zmenit hodnotu
for row = 1:size(matrixC, 1)
    for col = 1:size(matrixC, 2)
        % hodne duplikatniho kodu, ktereho nevim, jak se zbavit
        if matrixC(row, col) >= 0
            matrixC(row, col) = 1;
        elseif matrixC(row, col) < 0
            matrixC(row, col) = randi(10);
        end
    end
end
