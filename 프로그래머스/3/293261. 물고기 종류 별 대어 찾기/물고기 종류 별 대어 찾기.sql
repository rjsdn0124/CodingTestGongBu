-- 코드를 작성해주세요
SELECT fi.id, fni.fish_name, fi.length FROM FISH_INFO fi 
INNER JOIN FISH_NAME_INFO fni ON fi.fish_type = fni.fish_type 
WHERE (fi.fish_type, fi.length) 
IN (SELECT fi2.fish_type,  MAX(fi2.length) FROM FISH_INFO fi2 GROUP BY fi2.fish_type)
ORDER BY fi.id ASC;