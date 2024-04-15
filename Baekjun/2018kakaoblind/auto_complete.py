def solution(words):
    answer = 0
    words.sort()
    l = len(words)
    nj = 0
    for i in range(l-1):
        j = 0
        while j < len(words[i]) and words[i][j] == words[i+1][j]:
            j+=1
        if nj < j:
            answer += j + (0 if j == len(words[i]) else 1)
        else:
            answer += nj + 1
        nj = j
    answer += nj + 1
    return answer