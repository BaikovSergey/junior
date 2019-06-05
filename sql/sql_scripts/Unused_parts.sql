SELECT cb.body_name, ce.engine_name, ct.transmission_name
LEFT OUTER JOIN
"Car_body" AS cb
ON c.car_body = cb.body_id
WHERE c
LEFT OUTER JOIN
"Car_engine" AS ce
ON c.car_engine = ce.engine_id
LEFT OUTER JOIN 
"Car_transmission" AS ct
ON c.car_transmission = ct.transmission_id;