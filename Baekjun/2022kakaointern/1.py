def solution(survey, choices):
    answer = ''
    l = len(survey)
    arr={"RT":0,"CF":0,"JM":0,"AN":0}
    for i in range(l):
        s = survey[i]
        c = choices[i]
        if s == "RT" or s=="CF" or s=="JM" or s=="AN":
            arr[s] += 4-c
        elif s == "TR" or s=="FC" or s=="MJ" or s=="NA":
            arr[s[::-1]] += c-4
    for t in arr:
        if arr[t] >= 0:
            answer+=t[0]
        else:
            answer+=t[1]
    return answer