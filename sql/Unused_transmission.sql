SELECT ct.transmission_name
FROM "Car_transmission" AS ct
LEFT OUTER JOIN "Car" AS c
ON  c.car_id = ct.transmission_id
WHERE c.car_id IS null;