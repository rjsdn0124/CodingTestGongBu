-- 코드를 작성해주세요
SELECT YEAR(ed.differentiation_date) as year, 
(SELECT MAX(ed2.size_of_colony) from ECOLI_DATA ed2 WHERE year = YEAR(ed2.differentiation_date)) - ed.size_of_colony as year_dev
 , ed.id as id
FROM ECOLI_DATA ed
ORDER BY year ASC, year_dev ASC;