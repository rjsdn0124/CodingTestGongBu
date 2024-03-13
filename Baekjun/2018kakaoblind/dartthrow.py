def solution(dartResult):
    answer = 0
    n = len(dartResult)
    dic = {'S':1,'D':2,'T':3}
    
    i = 0
    prev_score = 0
    while i < n:
        c = dartResult[i]
        score = 0
        i += 1
        if dartResult[i] == '0':
            i += 1
            score = 10
        else:
            score = int(c)
            
        c = dartResult[i]
        score **= dic[c]
        
        if i + 1 < n:
            if dartResult[i+1] == '*':
                i += 1
                answer += prev_score
                score *= 2
            elif dartResult[i+1] == '#':
                i += 1
                score = -score
        answer += score
        prev_score = score
        i+=1
            
    return answer