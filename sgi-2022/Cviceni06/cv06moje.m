% (c) pzdrs bohac, marsel horvad - FOR REAL THIS TIME
close all;
clear all;

[data, Fs] = audioread('ovcaci-housle.wav');
window_size = 256; % windows size of 256 samples
overlap = 128; % each windows overlaps by this amount on both sides

zcr_result=[];
for n=1:overlap:numel(data)
    right_bound=n+window_size-1;
    if right_bound > numel(data)
        break
    end
    % current window within the whole data sample
    window_data=data(n:n+window_size-1);
    % zero crossing rate for the current window
    window_zcr=ZCR(window_data);
    zcr_result = [zcr_result window_zcr];
end

% present the results
subplot(3,1,1)
plot(data)
subplot(3,1,2)
plot(zcr_result)
subplot(3,1,3)
spectrogram( ...
    data, ...
    hamming(window_size), ...
    overlap, ...
    window_size, ...
    Fs, ...
    'yaxis' ...
);

function X = ZCR(x)
    % tady jsem byl tak pul hodiny, mel jsem:
    % abs(sign(x(n-sign(x(n-1))))
    % melo byt:
    % abs(sign(x(n))-sign(x(n-1)))
    % projedu cely okno a kontroluju u jakejch paru se meni znamenko, thats
    % it
    N=numel(x);
    sum=0;
    for n=2:N
        sum = sum + abs(sign(x(n))-sign(x(n-1)));
    end
    X=sum/(2*(N-1));
end