close all;
clear all;

patterns = ["c1-*.jpg", "c5-*.jpg", "c8-*.jpg"];
for pattern=1:length(patterns)
    filePattern = fullfile('data', patterns(pattern));
    theFiles = dir(filePattern);
    f=figure;
    last_subplot=0;
    for k = 1 : length(theFiles)
        baseFileName = theFiles(k).name;
        original_image=imread(append('data/', baseFileName));
    
        % preprocessing
        image=im2gray(original_image);
        image=imbinarize(image);
        
        [width, direction, area] = analyze(pixels(image));
        % random ass formula co jsem si vytah ze zadku
        correction = -direction*(60*(width/area)+width);
        
        corrected_image=imrotate(original_image, correction);
    
        % crop and remove black corners
        edge=ceil((size(corrected_image,2)-48)/2);
        corrected_image=corrected_image(edge:end-edge, edge:end-edge,:);
        corrected_image(corrected_image==0)=255;

        subplot(8,2,last_subplot+1); 
        imshow(original_image);
        subplot(8,2,last_subplot+2); 
        imshow(corrected_image);
        last_subplot=last_subplot+2;
    end
    truesize(f, [60 200])
end

function hits = pixels(A)
    hits = [];
    for x=1:size(A,2)
        for y=1:size(A, 1)
           if A(x, y) == 0
                hits = [hits; y x];
           end
        end
    end
end

function [width, direction, area] = analyze(A)
    area=numel(A);
    min_x=min(A(:,1));
    max_x=max(A(:,1));
    width=max_x-min_x;
    if A(1,1) > A(end,1)
        direction=-1;
    else
        direction=1;
    end
end