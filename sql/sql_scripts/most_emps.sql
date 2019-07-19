SELECT person.company_id,
company.name,
COUNT (company_id)
FROM
person
INNER JOIN company ON person.company_id = company.id
GROUP BY
company_id,
company.id
ORDER BY COUNT DESC
LIMIT 1;