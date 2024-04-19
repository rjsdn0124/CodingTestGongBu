def solution(genres, plays):
    answer = []
    dic = {}
    arr = []
    for i in range(len(genres)):
        g = genres[i]
        if not g in dic:
            dic[g] = [len(arr),[]]
            arr.append([0,g])
        arr[dic[g][0]][0]+=plays[i]
        na = dic[g][1]+[i]
        na.sort(key=lambda x: -plays[x])
        dic[g][1] = na[:2]
    arr.sort(reverse = True)
    for v,g in arr:
        answer += dic[g][1]
    return answer