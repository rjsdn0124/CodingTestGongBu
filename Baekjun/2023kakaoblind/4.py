def tostr(number):
    numstr= str(bin(number))[2:]
    count = 1
    while len(numstr) > count-1:
        count*=2
    return numstr.zfill(count-1)

def canmake(should_zero, numstr):
    mid = len(numstr)//2
    result = True
    print(numstr[mid])
    if numstr[mid] == "1" and should_zero == True:
        result = False
        return result
    if mid == 0:
        return result
    if numstr[mid] == "0":
        result = result and canmake(True,numstr[:mid])
        result = result and canmake(True,numstr[mid+1:])
    elif numstr[mid] == "1" and should_zero == False:
        result = result and canmake(False,numstr[:mid])
        result = result and canmake(False,numstr[mid+1:])

    return result
def solution(numbers):
    answer = []
    for number in numbers:
        numstr = tostr(number)
        print(numstr)
        answer.append(1 if canmake(False,numstr) else 0)
    return answer