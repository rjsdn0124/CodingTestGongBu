def solution(enroll, referral, seller, amount):
    answer = [0 for _ in range(len(enroll))]
    dic = {enroll[i]:i for i in range(len(enroll))}
    for i in range(len(seller)):
        s = seller[i]
        m = amount[i] * 100
        while not s == '-' and m > 0:
            donate = m // 10
            answer[dic[s]] += m-donate
            s = referral[dic[s]]
            m = donate
    return answer