select  Request_at as Day, round(count(if (Status != 'completed',true, null)) / count(*), 2) as 'Cancellation Rate' from Trips where Client_Id in (
	select Users_Id from Users where Banned = 'No'
) and Driver_id in (
	select Users_Id from Users where Banned = 'No'
) and Request_at BETWEEN '2013-10-01' AND '2013-10-03'
group by Request_at;