def update_emo_dis(emo_dis,emo_num):
    for i in range(emo_num):
        if emo_dis[i] == 3:
            if i == emo_num-1:
                return True,emo_dis
            emo_dis[i] = 0
        else:
            emo_dis[i] += 1
            break
    return False,emo_dis

def solution(users, emoticons):
    answer = [0,0]
    disc=[10,20,30,40]
    emo_num = len(emoticons)
    emo_dis=[0 for _ in range(emo_num)]
    isFin = False
    while not isFin:
        temp = [0,0]
        for user in users:
            usum=0
            for i in range(emo_num):
                if user[0] <= disc[emo_dis[i]]:
                    usum+=emoticons[i] * (100-disc[emo_dis[i]]) /100
            if user[1] <=usum:
                temp[0] +=1
            else:
                temp[1] += usum
        if answer[0] < temp[0]:
            answer = temp
        elif answer[0] == temp[0]:
            answer[1] = max(temp[1],answer[1])
        isFin,emo_dis=update_emo_dis(emo_dis,emo_num)
    return answer