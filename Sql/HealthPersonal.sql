CREATE DATABASE [Personal Health Assistant]
USE [Personal Health Assistant]
GO

CREATE TABLE [Roles]
(
	[Id] BIGINT PRIMARY KEY IDENTITY(1, 1) NOT NULL,
	[RoleName] VARCHAR(255)
)
GO

INSERT INTO [Roles] VALUES ('ROLE_USER')
INSERT INTO [Roles] VALUES ('ROLE_ADMIN')

CREATE TABLE [Users]
(
	[Id] BIGINT PRIMARY KEY IDENTITY(1, 1) NOT NULL,
	[Username] VARCHAR(255),
	[Email] VARCHAR(255),
	[Password] VARCHAR(255),
	[Name] VARCHAR(255),
	[Number] VARCHAR(20),
	[Gender] VARCHAR(10),
	[BirthDay] DATE,
	[Status] TINYINT,
	[Avatar] VARCHAR(MAX)
)
GO

CREATE TABLE [UserRole]
(
	[Id] BIGINT PRIMARY KEY IDENTITY(1, 1) NOT NULL,
	[User_id] BIGINT FOREIGN KEY REFERENCES [Users](id),
	[Role_id] BIGINT FOREIGN KEY REFERENCES [Roles](id),
)
GO

CREATE TABLE [Category] 
(
	[Id] BIGINT PRIMARY KEY IDENTITY(1, 1) NOT NULL,
	[Name] VARCHAR(255),
	[Slug] VARCHAR(255)
)
GO

CREATE TABLE [Feedback]
(
	[Id] BIGINT PRIMARY KEY IDENTITY(1, 1) NOT NULL,
	[User_id] BIGINT FOREIGN KEY REFERENCES [Users](id),
	[Feedback] VARCHAR(255)
)
GO

CREATE TABLE [IssuePersonal]
(
	[Id] BIGINT PRIMARY KEY IDENTITY(1, 1) NOT NULL,
	[CategoryId] BIGINT FOREIGN KEY REFERENCES [Category](Id),
	[Name] VARCHAR(255),
	[Description] TEXT,
	[Symptom] TEXT,
	[Prevention] TEXT,
	[Thumbnail] VARCHAR(MAX),
	[Status] TINYINT,
	[Slug] VARCHAR(255),
	[Highlight] INT
)
GO


CREATE TABLE [PersonalHealthVitals]
(
	[Id] BIGINT PRIMARY KEY IDENTITY(1, 1) NOT NULL,
	[User_id] BIGINT FOREIGN KEY REFERENCES [Users](id),
	[Weight] VARCHAR(255),
	[Height] VARCHAR(255),
	[BloodPressure] VARCHAR(255),
	[BloodGlucose] VARCHAR(255),
	[BloodOxygen] VARCHAR(255),
	[BodyTemperature] VARCHAR(255)
)

CREATE TABLE [AppointmentStatus]
(
	[NotificationId] BIGINT PRIMARY KEY IDENTITY(1, 1) NOT NULL,
	[UserId] BIGINT FOREIGN KEY REFERENCES [Users](id),
	[Date] DATE,
	[Time] VARCHAR(255),
	[Location] VARCHAR(255),
	[Description] VARCHAR(255)
)
GO

CREATE TABLE [MedicineDosages]
( 
	[UserId] BIGINT FOREIGN KEY REFERENCES [Users](id),
	[DosagesId] BIGINT PRIMARY KEY IDENTITY(1, 1) NOT NULL,
	[Medicine] VARCHAR(255),
	[TimesInADay] VARCHAR(255),
	[TimesPerWeek] VARCHAR(255),
	[DosageQuantity] VARCHAR(255)
)


insert into Category values ('Head',NULL)
insert into Category values ('Shoulder',NULL)
insert into Category values ('Arms',NULL)
insert into Category values ('Chest',NULL)
insert into Category values ('Stomach',NULL)
insert into Category values ('Legs',NULL)
insert into Category values ('Hands',NULL)

select * from Category

insert into IssuePersonal values(1,'Alzheimer','A brain disorder that slowly destroys memory and thinking skills and, eventually, the ability to carry out the simplest tasks','Forget about recent conversations or events, misplace items, forget the names of places and objects, have trouble thinking of the right word, ask questions repetitively','Currently ,there is no certain way to prevent the condition, but a healthy lifestyle can help reduce your risk',NULL,1,NULL,1)
insert into IssuePersonal values(1,'Dementias','The loss of cognitive functioning thinking, remembering, and reasoning to such an extent that it interferes with a person daily life and activities','Memory loss, difficulty concentrating, mood changes, being confused about time and place, struggling to follow a conversation or find the right word','There is no certain way to prevent all types of dementia, as researchers are still investigating how the condition develops',NULL,1,NULL,1)
insert into IssuePersonal values(1,'Brain Cancer','Headaches, nausea and balance problems','Headaches, seizures, vision or speech problems, persistently feeling sick, mental or behavioural changes','Unfortunately, you can not prevent a brain tumor, you can reduce your risk of developing a brain tumor by avoiding environmental hazards such as smoking and excessive radiation exposure',NULL,1,NULL,1)
insert into IssuePersonal values(1,'Mental Disorders','A behavioral or mental pattern that causes significant distress or impairment of personal functioning','Feeling sad or down, confused thinking or reduced ability to concentrate, withdrawal from friends and activities, extreme mood changes of highs and lows, excessive fears or worries or extreme feelings of guilt','Therapist, get help when you need it, take good care of yourself',NULL,1,NULL,0)
insert into IssuePersonal values(1,'Epilepsy and Other Seizure Disorders','A central nervous system (neurological) disorder in which brain activity becomes abnormal, causing seizures or periods of unusual behavior, sensations and sometimes loss of awareness','Temporary confusion, a staring spell, stiff muscles, loss of consciousness or awareness, uncontrollable jerking movements of the arms and legs','Take your medication as prescribed, anti-epileptic medications are designed to help prevent seizures,do not consume alcohol, practice stress management',NULL,1,NULL,1)

insert into IssuePersonal values(2,'Osteoarthritis','Is the most common form of arthritis','Joint tenderness, increased pain and stiffness when you have not moved your joints for a while, joints appearing slightly larger or more "knobbly" than usual','Exercise, posture, losing weight',NULL,1,NULL,0)
insert into IssuePersonal values(2,'Bursitis','A painful condition that affects the small, fluid-filled sacs called bursae (bur-SEE) that cushion the bones, tendons and muscles near your joints','Disabling joint pain, sudden inability to move a joint, a fever','Warm up before exercising, stop activities that cause pain, take breaks often when doing repetitive tasks',NULL,1,NULL,0)
insert into IssuePersonal values(2,'Impingement','A condition in which the tendons of the rotator cuff of the shoulder are pinched as they pass between the top of the upper arm (humerus) and the tip of the shoulder (acromion)','Pain in the top and outer side of your shoulder','Keeping proper posture and using proper shoulder mechanics when reaching and throwing',NULL,1,NULL,0)
insert into IssuePersonal values(2,'Dislocation','A separation of two bones where they meet at a joint','Joint is visibly deformed or out of place, numbness or tingling at the joint, swollen or discolored, limited ability to move, intense pain','Staying physically active to keep the muscles and tendons around the joints strong, wearing protective gear during contact sports',NULL,1,NULL,0)
insert into IssuePersonal values(2,'Frozen Shoulder','Occurs when the connective tissue enclosing the joint becomes thickened and tight','Pain and stiffness that make it difficult or impossible to move it','Stretching your shoulder and back muscles daily, maintaining a healthy immune system',NULL,1,NULL,0)

insert into IssuePersonal values(3,'Tendinitis','Is inflammation (swelling, heat) or irritation of a bursa','Pain is the main symptom, cause redness,warmth,or swelling in the affected area','Icing the area to reduce inflammation and pain',NULL,1,NULL,0)
insert into IssuePersonal values(3,'Sprains','A stretching or tearing of ligaments','Swelling, pain, redness, tenderness in the joint','Take breaks, rotate tasks when possible, so that you are using different muscle groups',NULL,1,NULL,0)
insert into IssuePersonal values(3,'Dislocation','A separation of two bones where they meet at a joint','Joint is visibly deformed or out of place, numbness or tingling at the joint, swollen or discolored, limited ability to move, intense pain','Staying physically active to keep the muscles and tendons around the joints strong, wearing protective gear during contact sports',NULL,1,NULL,0)
insert into IssuePersonal values(3,'Fractures','A break, usually in a bone','A visibly out-of-place or misshapen limb or joint, swelling, bruising, bleeding, intense pain, numbness and tingling','Consider balance training and physical therapy if your body feels off, keep your rooms picked up',NULL,1,NULL,0)
insert into IssuePersonal values(3,'Nerve problems','Occurs when a health condition affects the nerves that carry sensation to the brain','Often feels like a shooting, stabbing or burning sensation','Painkilling medicines, supplements and vitamins',NULL,1,NULL,1)

insert into IssuePersonal values(4,'Cystic Fibrosis','A disorder that damages your lungs, digestive tract and other organs','Wheezing, exercise intolerance, repeated lung infections, inflamed nasal passages or a stuffy nose','Cannot be prevented, however, genetic testing should be performed for couples who have CF or have relatives with the disease',NULL,1,NULL,1)
insert into IssuePersonal values(4,'Asthma','A condition in which your airways narrow and swell and may produce extra mucus','Wheezing, coughing and chest tightness becoming severe and constant, being too breathless to eat, speak or sleep, breathing fast, a fast heartbeat','Get vaccinated for influenza and pneumonia, identify and avoid asthma triggers',NULL,1,NULL,0)
insert into IssuePersonal values(4,'Bronchiectasis','Long-term condition where the airways of the lungs become widened, leading to a build-up of excess mucus that can make the lungs more vulnerable to infection','Shortness of breath, wheezing, coughing up blood or bloodstained phlegm, chest pain','There is no way to prevent congenital bronchiectasis, however, there are ways to help you avoid developing the lung damage that leads to bronchiectasis suck as vaccinate yourself, get medical treatment, stay away from smoke, fumes and gases',NULL,1,NULL,1)
insert into IssuePersonal values(4,'Cough','A voluntary or involuntary act that clears the throat and breathing passage of foreign particles, microbes, irritants, fluids, and mucus','A runny or stuffy nose, a feeling of liquid running down the back of your throat','Keep hydrated by drinking plenty of water, elevate your head with extra pillows when sleeping, use cough drops to soothe your throat',NULL,1,NULL,0)
insert into IssuePersonal values(4,'Chest Wall Cancer','Chest wall tumors can develop in the bones, soft tissues and cartilage of the chest cavity, which contains the heart, lungs and other organs','Pain or soreness in the chest area, swelling, impaired movement, a lump or bump protruding from the chest','Chemotherapy, radiation therapy and/or surgery',NULL,1,NULL,1)

insert into IssuePersonal values(5,'Esophageal Cancer','Cancer that occurs in the esophagus a long, hollow tube that runs from your throat to your stomach','Difficulty swallowing, weight loss without trying, worsening indigestion or heartburn, coughing or hoarseness','Avoid tobacco and alcohol, watch your diet, body weight, and physical activity, get treated for reflux or barrett esophagus',NULL,1,NULL,1)
insert into IssuePersonal values(5,'Gallstones','Hardened deposits of digestive fluid that can form in your gallbladder','Back pain between your shoulder blades, pain in your right shoulder, nausea or vomiting','A healthy, balanced diet is recommended, this includes plenty of fresh fruit and vegetables and wholegrains',NULL,1,NULL,0)
insert into IssuePersonal values(5,'Hiatal Hernia','Occurs when the upper part of your stomach bulges through your diaphragm into your chest cavity','Heartburn, regurgitation of food or liquids into the mouth, difficulty swallowing, chest or abdominal pain, Feeling full soon after you eat','Losing excess weight, not straining during bowel movements, getting help when lifting heavy objects',NULL,1,NULL,0)
insert into IssuePersonal values(5,'Gastroesophageal Reflux Disease','Occurs when stomach acid repeatedly flows back into the tube connecting your mouth and stomach (esophagus)','Upper abdominal or chest pain, trouble swallowing, sensation of a lump in your throat, a burning sensation in your chest','Stop smoking, elevate the head of your bed, start on your left side, do not lie down after a meal',NULL,1,NULL,0)
insert into IssuePersonal values(5,'Hemochromatosis','A condition in which the body takes up and stores more iron than it needs','Weight loss, weakness, joint pain, feeling very tired all the time','Procedure to remove some of your blood, known as a phlebotomy or venesection',NULL,1,NULL,1)

insert into IssuePersonal values(6,'Peripheral Artery Disease','Is the narrowing or blockage of the vessels that carry blood from the heart to the legs','Hair loss on your legs and feet,numbness or weakness in the legs,brittle, slow-growing toenails,shiny skin','Get plenty of physical activity',NULL,1,NULL,0)
insert into IssuePersonal values(6,'Pelvic Congestion Syndrome','A common cause of chronic pelvic pain in women of reproductive age','A dull, aching pain in your pelvic area and lower back, pain that gets worse during your period, discomfort or pain during sex','Use of compression garments during pregnancy',NULL,1,NULL,0)
insert into IssuePersonal values(6,'May-Thurner Syndome','A rare vascular condition that affects a vein in your pelvis','Achiness, heaviness, fatigue and swelling in the leg','Go see a doctor',NULL,1,NULL,0)
insert into IssuePersonal values(6,'Chronic Venous Insufficiency','Occurs when your leg veins do not allow blood to flow back up to your heart','Swelling in your legs or ankles, tight feeling in your calves or itchy, painful legs, pain when walking that stops when you rest, brown-colored skin, often near the ankles','Wear compression garments, maintain a healthy weight, increase activity levels, elevate the legs',NULL,1,NULL,0)
insert into IssuePersonal values(6,'Deep Vein Thrombosis','A medical condition that occurs when a blood clot forms in a deep vein','Swelling in 1 leg, warm skin around the painful area, red or darkened skin around the painful area','Stay a healthy weight, stay active, taking regular walks can help, drink plenty of fluids to avoid dehydration',NULL,1,NULL,0)

insert into IssuePersonal values(7,'Carpal Tunnel Syndrome','Is pressure on a nerve in your wrist','Tingling or numbness, weakness','Sleeping with your wrists held straight, keeping your wrists straight when using tools, avoiding flexing',NULL,1,NULL,0)
insert into IssuePersonal values(7,'Arthritis','Swelling and tenderness of one or more joints','Joint pain, tenderness and stiffness, joint pain, tenderness and stiffness, restricted movement of the joints, warm red skin over the affected joint','Stay at a healthy weight, control your blood sugar, exercise, stretch, avoid injury',NULL,1,NULL,0)
insert into IssuePersonal values(7,'Trigger Finger','Is a condition that affects one or more of the hand tendons, making it difficult to bend the affected finger or thumb','Pain at the base of the affected finger or thumb when you move it or press on it, and stiffness or clicking when you move the affected finger or thumb, particularly first thing in the morning','Take rest breaks, avoid overuse of the wrist and fingers and be sure to use the appropriate hand tools for the job',NULL,1,NULL,0)
insert into IssuePersonal values(7,'Dupuytren Disease','A hand deformity that usually develops over years','Lumps, nodules, and bands or cords on the palmar side of the hands','There are no proven ways to prevent Dupuytren Disease or limit its progress, hand therapy and rehabilitation using thermoplastic night splints and regular physiotherapy exercises may aid in the postoperative recovery period',NULL,1,NULL,1)
insert into IssuePersonal values(7,'Ganglion Cysts','A fluid-filled swelling that usually develops near a joint or tendon','Lump size, pain, texture, mobility','Quitting smoking, resting the wrists and fingers after long periods of exertion, stretching the hands, wrists and finger joints regularly',NULL,1,NULL,0)

select * from IssuePersonal