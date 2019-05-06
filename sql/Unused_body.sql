SELECT cb.body_name
FROM "Car_body" AS cb
LEFT OUTER JOIN "Car" AS c
ON  c.car_id = cb.body_id
WHERE c.car_id IS null;
