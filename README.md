## Allowance 

*Micronaut CRUD service to track allowances and perform lookups/updates*

**Includes scheduled jobs:**
* update balances
* create daily tasks

**Allowance is age based.  Each week age is divided by total tasks assigned.**  
* One increment is deducted from total possible for each incomplete task.   
* .5 increment is deducted from total possible for each task completed poorly.  
 

