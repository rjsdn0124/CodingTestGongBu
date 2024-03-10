def solution(bandage, health, attacks):
    answer = 0
    last_att_time = attacks[-1][0]
    now_health = health
    cont = 0
    
    for i in range(last_att_time+1):
        attack = attacks[0]
        if attack[0] == i:
            now_health -= attack[1]
            cont = 0
            attacks = attacks[1:]
            if now_health <= 0:
                return -1
        else:
            cont += 1
            now_health += bandage[1]
            if cont == bandage[0]:
                now_health += bandage[2]
                cont = 0
            if now_health >= health:
                now_health = health
                cont = 0
    return now_health