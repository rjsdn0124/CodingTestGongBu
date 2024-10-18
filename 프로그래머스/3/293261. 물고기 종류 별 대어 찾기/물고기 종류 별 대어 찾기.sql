-- 코드를 작성해주세요
SELECT fi.id, fni.fish_name, fi.length FROM FISH_INFO fi 
INNER JOIN FISH_NAME_INFO fni ON fi.fish_type = fni.fish_type 
WHERE fi.length = 
(SELECT MAX(fi2.length) FROM FISH_INFO fi2 WHERE fi.fish_type = fi2.fish_type GROUP BY fi2.fish_type)
ORDER BY fi.id ASC;