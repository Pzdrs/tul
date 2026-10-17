% https://www.youtube.com/watch?v=P8hT5nDai6A
function [k,q] = lin_regression(x,y)
    % prevedu radkove vektory na sloupcove - zadani rikalo osetrit tak
    % osetruju
    if (isrow(x)) x = x'; end
    if (isrow(y)) y = y'; end
    
    n = numel(x);
    sumX = sum(x);
    sumX2 = sum(x.^2);
    sumXY = sum(x .* y);
    sumY = sum(y);

    jmenovatel = n * sumX2 - sumX^2;
    % cleny predpisu primky podle vzorecku
    % linearni clen
    k = (n * sumXY - sumX * sumY) / jmenovatel; 
    % absolutni clen
    q = (sumX2 * sumY - sumX * sumXY) / jmenovatel; 
end