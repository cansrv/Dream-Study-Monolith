# Dream-Study
This is the repository created for Dream Games backend case study which has a monolithic structure.
# Introduction
Original plan was to create the backend in a microservice based architecture, which is probably prefered in such an application. However I have more experience in monolithic systems and that is the story behind "Monolithic" word in the repository. I tried my best to ensure that the application is easy to split into microservices.
# Problems and Solutions
Functionality-wise:
-  createUser: was the easiest functionality to complete with no problems occured during implementation. With that said, selection of indexed columns took some time. In the end I've decided to index username column.
-  participateInTournament: was the hardest functionality to implement in an efficient manner. Since the main objects (Tournament and Player) are rather tightly-bounded, I've decided to decouple them over Participant class.
-  claimReward: was also a rather easy to implement.
-  levelUp: was also a tightly coupled functionality where one needs to consider the country score, tournament score and player score. This functionality is also bound to create concurrency issues (e.g. two players of same origin level up simultaneously).
-  endTournament: was a database intensive functionality.

# Things I Could Have Done Better
I've started working on the case study quite late (about 6 hours ago); which resulted in poor planning stage, which resulted in sub-optimal architecture design. This is also the reason why Unit Tests are missing. If the deadline is until the end of day 10, most of the aforementioned problems will be solved. Otherwise, commit with message "Completed the app" on dev branch should be evaluated. Thats all, thank you. 
