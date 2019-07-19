SELECT
person.name,
company.name
FROM
person
INNER JOIN company ON company.id = person.company_id
WHERE
company.id != 5;