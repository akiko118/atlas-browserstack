-- {user = Lauren Cook,					PID = 1842603, cPayrollidUS		= 20323}   
-- {user = Julien Doucette-Preville,	PID = 2027143, cPayrollidCan	= 9240}    
-- {user = Nicholas Faulkner,			PID = 1717121, cPayrollidEurope	= 142} 
-- {user = Angelique Brouwer,			PID = 1849919, cPayrollidEurope = 166}
-- {user = Amanda Arling,				PID = 1719718, cPayrollidUS     = 20193 }

--sqlcmd -S ibis6 -U appuser -P appuser

--:setvar codebase "C:\Users\bereynia\workspace\DatabaseScripts\release management\Issues\Issue 2868 Testing" 
:setvar codebase "C:\br_db\DatabaseScripts\release management\Issues\Issue 2868 Testing"
--:setvar codebase "C:\Users\hartwele\Documents\GitHub\DatabaseScripts\release management\Issues\Issue 2868 Testing" 

USE [ibiscopy]
GO

:setvar iLeaderPartyID_IN 1948042
:setvar iPayPeriodID 486


:r $(codebase)\"ALL-1 Unsubmit Activities.sql"
GO
:r $(codebase)\"ALL-2 List Activities.sql"
GO
:r $(codebase)\"ALL-3 Submit Activities.sql"
GO
:r $(codebase)\"ALL-2 List Activities.sql"
GO
:r $(codebase)\"Export_Locale.sql"
GO