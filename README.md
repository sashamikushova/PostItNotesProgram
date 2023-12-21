Service to track upcoming tasks with notifications.

The program was written using Spring Boot for making project, Vaadin for UI design and MongoDB for storing information about users and their tasks. 
It includes 4 web pages: for signing uop, logging in, saving tasks and managing push notifications. If user is subscribed they will receive task push notifications 2 minutes before the actual time of a task. The notifications are visible even if the window is closed.

To start the program run the file JavaPostItNotes.
Make sure to also make an application.properties file in the resourses package. There, values for fields VAPID_PUBLIC_KEY, VAPID_PRIVATE_KEY (generate them with npx web-push generate-vapid-keys), VAPID_SUBJECT(your e-mail) should be provided. Please also add a link in spring.data.mongodb.uri field in application.properties file to a running MongoDB database (If you use local, make sure it is running).

<img width="1285" alt="Screenshot 2023-12-21 at 21 31 57" src="https://github.com/sashamikushova/PostItNotesProgram/assets/113534190/9d4785ab-0f2c-4112-9560-6cee1e00d40c">
<img width="1355" alt="Screenshot 2023-12-21 at 21 31 30" src="https://github.com/sashamikushova/PostItNotesProgram/assets/113534190/9894b366-36bb-4553-9fa2-fa7811cc6ebe">
<img width="1326" alt="Screenshot 2023-12-21 at 21 31 45" src="https://github.com/sashamikushova/PostItNotesProgram/assets/113534190/cdcb1442-53a9-4c23-b560-da1cd100d68f">
<img width="344" alt="Screenshot 2023-12-21 at 22 10 01" src="https://github.com/sashamikushova/PostItNotesProgram/assets/113534190/24b7be3c-879d-4134-aa37-e23fe19a1edb">
<img width="1415" alt="Screenshot 2023-12-21 at 22 11 12" src="https://github.com/sashamikushova/PostItNotesProgram/assets/113534190/04829410-060d-47c2-a51b-ef57452641b2">
<img width="1337" alt="Screenshot 2023-12-21 at 22 12 26" src="https://github.com/sashamikushova/PostItNotesProgram/assets/113534190/298328a6-3fcb-4594-9183-c8e52808212a">
