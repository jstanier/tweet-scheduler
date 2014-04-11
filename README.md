tweet-scheduler
===============

Work out when it's best to schedule your tweets. 

### Input

A CSV file in the format

    rank,"The content of your tweet"
    
Where rank is in numerical order of importance of the content you want to post.

### Output

A schedule for when you should post those tweets, starting with the day after the current day. For example:
```
Sat, 12 April 2014 14:00 - Excited to announce that @thesearchagency has selected Brandwatch as its new platform for earned media monitoring - http://bit.ly/1gIh4j2
Sat, 12 April 2014 17:00 - How many people use social networks to get news? pic.twitter.com/tGIEpnXGYu
Sat, 12 April 2014 20:00 - Our NY office has been chosen as an inspiring work environment - http://bit.ly/1s6VvBV
Sat, 12 April 2014 21:00 - The complete guide to analytics in digital marketing - http://bit.ly/1eoP9dv  via @linkhumans
Sun, 13 April 2014 14:00 - Register for our Masterclass event in London now! bit.ly/34d
Sun, 13 April 2014 17:00 - It seems that competitions are dominating Mother's Day chat in the UK pic.twitter.com/swB1lAYJcQ
Sun, 13 April 2014 20:00 - Wells Fargo treats its social media command center like a business. #smcc  http://ow.ly/voIlu  pic.twitter.com/ffUtIuBUUh
Sun, 13 April 2014 21:00 - 7 1/2 marketing tactics to prepare for mass exposure - http://ow.ly/vEoJ1  via @Aria_Agency
Mon, 14 April 2014 14:00 - Are you a juggling genius? Perhaps you’re a first class plate-spinner? If so, we want to hear from you! http://bit.ly/1lysJt8
Mon, 14 April 2014 17:00 - Before the Internet - http://xkcd.com/1348/
Mon, 14 April 2014 20:00 - We’re thrilled our customers have ranked us highest for customer satisfaction in latest @G2Crowd report http://ow.ly/v3Ntg
Mon, 14 April 2014 21:00 - Live debates provide a snapshot of how social media can give large-scale instant feedback - http://ow.ly/v43nt  by @eoghanlondon
Tue, 15 April 2014 14:00 - Excited for our Masterclass at @oiconf on Thursday. You can still get tickets here - http://bit.ly/1elyQZk
Tue, 15 April 2014 17:00 - Mesmerizing time-lapse video of fireflies - http://ow.ly/v8mWI
Tue, 15 April 2014 20:00 - Creation Pinpoint's growth cited in our @Gnip partnership story - http://bit.ly/1hyEGHm  by @engagementstrat
Tue, 15 April 2014 21:00 - Guy games Uber’s referral system, racks up $50K in free rides - http://ow.ly/vEGS7
Wed, 16 April 2014 14:00 - Ten DOs and DON’Ts in social media analytics - http://ow.ly/vqW9o
Wed, 16 April 2014 17:00 - What if I look at my social data, and there are no interesting trends? http://bit.ly/1juVvdg
Wed, 16 April 2014 20:00 - Ping pong ceiling #insidebrandwatch. What do you think? pic.twitter.com/Uo3NkAnxgq
Wed, 16 April 2014 21:00 - With Turkey at a crossroads politically, free speech rights at stake - http://ow.ly/v3zsA  via @pbsmediashift
Thu, 17 April 2014 09:00 - Rise of the robots - http://ow.ly/v8ElZ
Thu, 17 April 2014 14:00 - We've launched Brandwatch Demographics! See more here: youtube.com/fff
Thu, 17 April 2014 17:00 - Turkey moves to block YouTube but attempt fails - http://n.pr/1pAmOAm  via @AP
Thu, 17 April 2014 20:00 - @bryantookey from @brandwatch on monitoring at #icom14 pic.twitter.com/9mfKh0UFRH
Thu, 17 April 2014 21:00 - Almost one third of travelers displayed either advocacy for or dissatisfaction with the brand they just flew -  http://ow.ly/vF4ke 
Fri, 18 April 2014 09:00 - 58% of those who consume news in social media are women  - http://ow.ly/v8H9Q  pic.twitter.com/IP55pSRTsr
Fri, 18 April 2014 14:00 - Check out our interview with our CEO: bit.ly/dwa
Fri, 18 April 2014 17:00 - Facebook looks to drones, lasers and satellites for Internet access - http://tcrn.ch/1mb4wFO
Fri, 18 April 2014 20:00 - Nominations are open for the @Tech4GoodAwards. Do you know anyone who deserves some recognition? http://bit.ly/1eAjyQe
Fri, 18 April 2014 21:00 - Claustrophobic? Don't ride Tokyo's subway during rush hour - http://ow.ly/vEHBc
```
### Where did you get the statistics from?

This current version uses statistics for the best times to post for click-through rate (CTR), which was found in a [Huffington Post article](http://www.huffingtonpost.com/belle-beth-cooper/a-scientific-guide-to-pos_b_4262571.html).

The particular graphs that I used to pick priority timeslots were these:

![Best days of the week to post for CTR](http://blog.bufferapp.com/wp-content/uploads/2013/08/Screen-Shot-2013-08-16-at-10.48.52-AM.png "Best days of the week to post for CTR")
![Best times of the day to post for CTR](http://blog.bufferapp.com/wp-content/uploads/2013/08/tweet-times.jpeg "Best times of the day to post for CTR")
