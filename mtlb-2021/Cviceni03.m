% 1)
A = randn(10, 10);

% 2)
B = round(A);

% 3)
for i = 1:size(B, 2)
    swapped = false;
    for col = 1:size(B, 2)
        for row = 1:size(B, 1)
            if col + 1 > size(B,2) 
                break 
            end
            b1 = B(row, col);
            b2 = B(row, col + 1);

            if b1 == b2 ;continue
                elseif b1 > b2 ;break
                elseif b1 < b2
                temp = B(:, col);
                B(:, col) = B(:, col + 1);
                B(:, col + 1) = temp;
                swapped = true;
                break
            end
        end
    end
    if ~swapped
        break
    end
end
B