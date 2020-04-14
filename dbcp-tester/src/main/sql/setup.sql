CREATE OR REPLACE function next_run_time(p_date in timestamp default current_timestamp, p_interval in varchar default 'MONTH') returns timestamp as
    $body$
begin
    return current_timestamp;
end;
$body$
language plpgsql
;