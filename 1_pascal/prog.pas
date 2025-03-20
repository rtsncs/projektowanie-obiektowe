program Prog(output);

type intarray = array of integer;

var numbers: intarray;
var i: integer;

procedure GenerateNumbers(var numbers: intarray; min, max, count: integer);
var i: integer;
begin
    SetLength(numbers, count);
    for i := Low(numbers) to High(numbers) do
    begin
        numbers[i] := Random(max - min + 1) + min;
    end;
end;

procedure BubbleSort(var numbers: intarray);
var i, temp: integer;
var sorted: boolean;
begin
    repeat
        sorted := true;
        for i := Low(numbers) to High(numbers) - 1 do
        begin
            if numbers[i] > numbers[i + 1] then
            begin
                temp := numbers[i];
                numbers[i] := numbers[i + 1];
                numbers[i + 1] := temp;
                sorted := false;
            end;
        end;
    until sorted;
end;

procedure TestGenerateNumbersBounds;
var testNum: intarray;
var i: integer;
begin
    GenerateNumbers(testNum, 0, 50, 100);
    for  i := Low(testNum) to High(testNum) do
    begin
        if (testNum[i] < 0) or (testNum[i] > 50) then
        begin
            WriteLn('TestGenerateNumbersBounds failed');
            SetLength(testNum, 0);
            break;
        end;
    end;
    WriteLn('TestGenerateNumbersBounds passed');
    SetLength(testNum, 0);
end;

procedure TestGenerateNumbersCount;
var testNum: intarray;
begin
    GenerateNumbers(testNum, 0, 50, 100);
    if Length(testNum) <> 100 then
    begin
        WriteLn('TestGenerateNumbersCount failed');
    end
    else
    begin
        WriteLn('TestGenerateNumbersCount passed');
    end;
    SetLength(testNum, 0);
end;

procedure TestBubbleSort;
var testNum: intarray;
var i: integer;
begin
    GenerateNumbers(testNum, 0, 50, 100);
    BubbleSort(testNum);
    for  i := Low(testNum) to High(testNum) - 1 do
    begin
        if testNum[i] > testNum[i + 1] then
        begin
            WriteLn('TestBubbleSort failed');
            SetLength(testNum, 0);
            break;
        end;
    end;
    WriteLn('TestBubbleSort passed');
    SetLength(testNum, 0);
end;

procedure TestBubbleSortLength;
var testNum: intarray;
begin
    GenerateNumbers(testNum, 0, 50, 100);
    BubbleSort(testNum);
    if Length(testNum) <> 100 then
    begin
        WriteLn('TestBubbleSortLength failed');
    end
    else
    begin
        WriteLn('TestBubbleSortLength passed');
    end;
    SetLength(testNum, 0);
end;

procedure TestBubbleSortNegative;
var testNum: intarray;
var i: integer;
begin
    GenerateNumbers(testNum, -50, 50, 100);
    BubbleSort(testNum);
    for  i := Low(testNum) to High(testNum) - 1 do
    begin
        if testNum[i] > testNum[i + 1] then
        begin
            WriteLn('TestBubbleSortNegative failed');
            SetLength(testNum, 0);
            break;
        end;
    end;
    WriteLn('TestBubbleSortNegative passed');
    SetLength(testNum, 0);
end;

begin
    Randomize;
    
    TestGenerateNumbersBounds;
    TestGenerateNumbersCount;
    TestBubbleSort;
    TestBubbleSortLength;
    TestBubbleSortNegative;

    GenerateNumbers(numbers, 0, 100, 50);
    BubbleSort(numbers);

    for i := Low(numbers) to High(numbers) do
    begin
        Write(numbers[i], ' ');
    end;
    WriteLn
end.
