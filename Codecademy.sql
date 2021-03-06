USE [master]
GO
/****** Object:  Database [Codecademy]    Script Date: 18/04/2021 15:48:36 ******/
CREATE DATABASE [Codecademy]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Codecademy', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Codecademy.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Codecademy_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Codecademy_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Codecademy] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Codecademy].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Codecademy] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Codecademy] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Codecademy] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Codecademy] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Codecademy] SET ARITHABORT OFF 
GO
ALTER DATABASE [Codecademy] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Codecademy] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Codecademy] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Codecademy] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Codecademy] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Codecademy] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Codecademy] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Codecademy] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Codecademy] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Codecademy] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Codecademy] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Codecademy] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Codecademy] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Codecademy] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Codecademy] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Codecademy] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Codecademy] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Codecademy] SET RECOVERY FULL 
GO
ALTER DATABASE [Codecademy] SET  MULTI_USER 
GO
ALTER DATABASE [Codecademy] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Codecademy] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Codecademy] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Codecademy] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Codecademy] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Codecademy] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Codecademy', N'ON'
GO
ALTER DATABASE [Codecademy] SET QUERY_STORE = OFF
GO
USE [Codecademy]
GO
/****** Object:  Table [dbo].[Certificate]    Script Date: 18/04/2021 15:48:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Certificate](
	[CertificateId] [int] IDENTITY(1,1) NOT NULL,
	[Grade] [float] NOT NULL,
	[Employee] [nvarchar](50) NOT NULL,
	[StudentEmailAddress] [nvarchar](50) NOT NULL,
	[CourseName] [nvarchar](50) NOT NULL,
	[EnrollmentDate] [date] NOT NULL,
 CONSTRAINT [PK_Certificate_1] PRIMARY KEY CLUSTERED 
(
	[CertificateId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Content]    Script Date: 18/04/2021 15:48:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Content](
	[ContentItemId] [int] IDENTITY(1,1) NOT NULL,
	[PublicationDate] [date] NOT NULL,
	[Status] [nvarchar](25) NOT NULL,
	[Type] [nvarchar](20) NOT NULL,
	[CourseName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Content] PRIMARY KEY CLUSTERED 
(
	[ContentItemId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Course]    Script Date: 18/04/2021 15:48:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course](
	[CourseName] [nvarchar](50) NOT NULL,
	[Module] [int] NULL,
	[Webcast] [int] NULL,
	[Subject] [nvarchar](50) NOT NULL,
	[Difficulty] [nvarchar](20) NOT NULL,
	[IntroductionText] [nvarchar](255) NULL,
 CONSTRAINT [PK_Course] PRIMARY KEY CLUSTERED 
(
	[CourseName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Enrollment]    Script Date: 18/04/2021 15:48:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Enrollment](
	[EmailAddress] [nvarchar](50) NOT NULL,
	[CourseName] [nvarchar](50) NOT NULL,
	[EnrollmentDate] [date] NOT NULL,
 CONSTRAINT [PK_Enrollment] PRIMARY KEY CLUSTERED 
(
	[EmailAddress] ASC,
	[CourseName] ASC,
	[EnrollmentDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[InterestingCourse]    Script Date: 18/04/2021 15:48:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InterestingCourse](
	[CourseName] [nvarchar](50) NOT NULL,
	[InterestingCourseName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_InterestingCourse] PRIMARY KEY CLUSTERED 
(
	[CourseName] ASC,
	[InterestingCourseName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Module]    Script Date: 18/04/2021 15:48:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Module](
	[Title] [nvarchar](50) NOT NULL,
	[Version] [float] NOT NULL,
	[Description] [nvarchar](255) NULL,
	[ContactName] [nvarchar](50) NULL,
	[ContactEmail] [nvarchar](50) NULL,
	[ContentItemId] [int] NULL,
	[CourseName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Module] PRIMARY KEY CLUSTERED 
(
	[Title] ASC,
	[Version] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Progress]    Script Date: 18/04/2021 15:48:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Progress](
	[StudentEmailAddress] [nvarchar](50) NOT NULL,
	[ContentItemId] [int] NOT NULL,
	[ProgressPercentage] [float] NOT NULL,
 CONSTRAINT [PK_Progress] PRIMARY KEY CLUSTERED 
(
	[StudentEmailAddress] ASC,
	[ContentItemId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Student]    Script Date: 18/04/2021 15:48:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Student](
	[EmailAddress] [nvarchar](50) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[DateOfBirth] [date] NOT NULL,
	[Gender] [nvarchar](50) NOT NULL,
	[City] [nvarchar](50) NOT NULL,
	[Address] [nvarchar](50) NOT NULL,
	[Country] [nvarchar](50) NOT NULL,
	[PostalCode] [nvarchar](7) NOT NULL,
 CONSTRAINT [PK_Student] PRIMARY KEY CLUSTERED 
(
	[EmailAddress] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Webcast]    Script Date: 18/04/2021 15:48:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Webcast](
	[Title] [nvarchar](50) NOT NULL,
	[Duration] [int] NOT NULL,
	[Description] [nvarchar](255) NULL,
	[LecturerName] [nvarchar](50) NULL,
	[LecturerOrg] [nvarchar](50) NULL,
	[ContentItemId] [int] NOT NULL,
	[ViewCount] [int] NULL,
 CONSTRAINT [PK_Webcast] PRIMARY KEY CLUSTERED 
(
	[Title] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Certificate] ON 

INSERT [dbo].[Certificate] ([CertificateId], [Grade], [Employee], [StudentEmailAddress], [CourseName], [EnrollmentDate]) VALUES (2, 5, N'Arno', N'thijsvanrijsbergen@gmail.com', N'Cybersecurity basics explained', CAST(N'2021-04-17' AS Date))
SET IDENTITY_INSERT [dbo].[Certificate] OFF
GO
SET IDENTITY_INSERT [dbo].[Content] ON 

INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (1, CAST(N'2021-04-17' AS Date), N'Active', N'Module', N'Learn Python 3')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (2, CAST(N'2021-04-17' AS Date), N'Active', N'Module', N'Learn Python 3')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (3, CAST(N'2021-04-17' AS Date), N'Active', N'Webcast', N'Learn Python 3')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (4, CAST(N'2021-04-17' AS Date), N'Active', N'Webcast', N'Learn Python 3')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (5, CAST(N'2021-04-17' AS Date), N'Active', N'Module', N'Front-End Engineer')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (6, CAST(N'2021-04-17' AS Date), N'Active', N'Module', N'Front-End Engineer')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (7, CAST(N'2021-04-17' AS Date), N'Active', N'Webcast', N'Front-End Engineer')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (8, CAST(N'2021-04-17' AS Date), N'Active', N'Module', N'Learn Intermidiate CSS')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (9, CAST(N'2021-04-17' AS Date), N'Active', N'Module', N'Learn Intermidiate CSS')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (10, CAST(N'2021-04-17' AS Date), N'Active', N'Module', N'Learn Advanced CSS')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (11, CAST(N'2021-04-17' AS Date), N'Active', N'Module', N'Learn Advanced CSS')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (12, CAST(N'2021-04-17' AS Date), N'Active', N'Webcast', N'Learn Advanced CSS')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (13, CAST(N'2021-04-17' AS Date), N'Active', N'Module', N'Design Databases With PostgreSQL')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (14, CAST(N'2021-04-17' AS Date), N'Active', N'Module', N'Design Databases With PostgreSQL')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (15, CAST(N'2021-04-17' AS Date), N'Active', N'Module', N'Design Databases With PostgreSQL')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (16, CAST(N'2021-04-17' AS Date), N'Active', N'Module', N'Learn Java')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (17, CAST(N'2021-04-17' AS Date), N'Active', N'Module', N'Learn Java')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (18, CAST(N'2021-04-17' AS Date), N'Active', N'Webcast', N'Learn Java')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (19, CAST(N'2021-04-17' AS Date), N'Active', N'Webcast', N'Learn Java')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (20, CAST(N'2021-04-17' AS Date), N'Active', N'Webcast', N'Learn Java')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (21, CAST(N'2021-04-17' AS Date), N'Active', N'Module', N'Introduction to Cybersecurity')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (22, CAST(N'2021-04-17' AS Date), N'Active', N'Module', N'Introduction to Cybersecurity')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (23, CAST(N'2021-04-17' AS Date), N'Active', N'Module', N'Introduction to Cybersecurity')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (24, CAST(N'2021-04-17' AS Date), N'Active', N'Module', N'Introduction to Cybersecurity')
SET IDENTITY_INSERT [dbo].[Content] OFF
GO
INSERT [dbo].[Course] ([CourseName], [Module], [Webcast], [Subject], [Difficulty], [IntroductionText]) VALUES (N'Design Databases With PostgreSQL', 1, 1, N'SQL', N'Hard', N'Learn how to create entire databases')
INSERT [dbo].[Course] ([CourseName], [Module], [Webcast], [Subject], [Difficulty], [IntroductionText]) VALUES (N'Front-End Engineer', 1, 1, N'Front-End', N'Medium', N'Front-End development is important for companies')
INSERT [dbo].[Course] ([CourseName], [Module], [Webcast], [Subject], [Difficulty], [IntroductionText]) VALUES (N'Introduction to Cybersecurity', 1, 1, N'Cybersecurity', N'Medium', N'Data is valuable and Cybersecurity helps to protect it')
INSERT [dbo].[Course] ([CourseName], [Module], [Webcast], [Subject], [Difficulty], [IntroductionText]) VALUES (N'Learn Advanced CSS', 1, 1, N'CSS', N'Hard', N'Advanced CSS cours for proffesionals')
INSERT [dbo].[Course] ([CourseName], [Module], [Webcast], [Subject], [Difficulty], [IntroductionText]) VALUES (N'Learn Intermidiate CSS', 1, 1, N'CSS', N'Medium', N'Basics of CSS coding')
INSERT [dbo].[Course] ([CourseName], [Module], [Webcast], [Subject], [Difficulty], [IntroductionText]) VALUES (N'Learn Java', 1, 1, N'Java', N'Easy', N'The very basics of Java')
INSERT [dbo].[Course] ([CourseName], [Module], [Webcast], [Subject], [Difficulty], [IntroductionText]) VALUES (N'Learn Python 3', 1, 1, N'Python', N'Easy', N'Learn the basics of coding with Python')
INSERT [dbo].[Course] ([CourseName], [Module], [Webcast], [Subject], [Difficulty], [IntroductionText]) VALUES (N'Test', NULL, NULL, N'Test Subject', N'Easy', N'This is just a test')
GO
INSERT [dbo].[Enrollment] ([EmailAddress], [CourseName], [EnrollmentDate]) VALUES (N'liamgevaerts@gmail.com', N'Learn Advanced CSS', CAST(N'2021-04-18' AS Date))
INSERT [dbo].[Enrollment] ([EmailAddress], [CourseName], [EnrollmentDate]) VALUES (N'ricardovandeworp@gmail.com', N'Learn Intermidiate CSS', CAST(N'2021-04-18' AS Date))
INSERT [dbo].[Enrollment] ([EmailAddress], [CourseName], [EnrollmentDate]) VALUES (N'thijsvanrijsbergen@gmail.com', N'Introduction to Cybersecurity', CAST(N'2021-04-17' AS Date))
GO
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'Design Databases With PostgreSQL', N'Introduction to Cybersecurity')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'Design Databases With PostgreSQL', N'Learn Java')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'Front-End Engineer', N'Learn Python 3')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'Introduction to Cybersecurity', N'Design Databases with PostgreSQL')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'Introduction to Cybersecurity', N'Learn Java')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'Learn Advanced CSS', N'Learn Intermidiate CSS')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'Learn Intermidiate CSS', N'Learn Advanced CSS')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'Learn Java', N'Design Databases with PostgreSQL')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'Learn Java', N'Introduction to Cybersecurity')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'Learn Python 3', N'Front-End Engineer')
GO
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Advanced CSS 1', 1, N'Advanced CSS module 1', N'Ruud', N'ruud@gmail.com', 10, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Advanced CSS 2', 1, N'Advanced CSS module 2', N'Ruud', N'ruud@gmail.com', 11, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'CSS basics 1', 1, N'Learn Intermidiate CSS module 1', N'Ruud', N'ruud@gmail.com', 8, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'CSS basics 2', 3, N'Learn Intermidiate CSS module 2', N'Ruud', N'ruud@gmail.com', 9, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'CSS new lesson', 1, N'CSS basic lesson', N'Ruud', N'ruud@gmail.com', NULL, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Cybersecurity module 1', 2, N'Cybersecurity module 1', N'Dion', N'dion@gmail.com', 21, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Cybersecurity module 2', 1, N'Cybersecurity module 2', N'Dion', N'dion@gmail.com', 22, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Cybersecurity module 3', 1, N'Cybersecurity module 3', N'Dion', N'dion@gmail.com', 23, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Cybersecurity module 4', 1, N'Cybersecurity module 4', N'Dion', N'dion@gmail.com', 24, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Cybersecurity new lesson', 1, N'Cybersecurity the basics', N'Dion', N'dion@gmail.com', NULL, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Front-End module 1', 1, N'Front-End module 1', N'Marjolein', N'marjolein@gmail.com', 5, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Front-End module 2', 1, N'Front-End module 2', N'Marjolein', N'marjolein@gmail.com', 6, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Front-End new lesson', 1, N'Front-End the basics', N'Marjolein', N'marjolein@gmail.com', NULL, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Java module 1', 1, N'Writing your a Java page', N'Robin', N'Robin@gmail.com', 16, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Java module 2', 1, N'Creating a button in Java', N'Marjolein', N'marjolein@gmail.com', 17, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Front-End new lesson', 1, N'Front-End the basics', N'Marjolein', N'marjolein@gmail.com', NULL, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'MSSQL creating a Database Diagram', 2, N'Database Diagram', N'Robin', N'robin@gmail.com', 13, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'PostgreSQL introduction', 1, N'Learning how to create a database', N'Marjolein', N'marjolein@gmail.com', 14, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'SQL new lesson', 1, N'SQL the basics', N'Marjolein', N'marjolein@gmail.com', NULL, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Python explanation 1', 3, N'Python explanation module 1', N'Robin', N'robin@gmail.com', 1, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Python explanation 2', 1, N'Python explanation module 2', N'Robin', N'robin@gmail.com', 2, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Python new lesson', 1, N'Python the basics', N'Robin', N'robin@gmail.com', NULL, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Test module', 1, N'Test module', N'Dion', N'dion@gmail.com', NULL, N'Test')
GO
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'liamgevaerts@gmail.com', 10, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'liamgevaerts@gmail.com', 11, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'liamgevaerts@gmail.com', 12, 20)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'liamgevaerts@gmail.com', 13, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'liamgevaerts@gmail.com', 14, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'liamgevaerts@gmail.com', 21, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'liamgevaerts@gmail.com', 22, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'liamgevaerts@gmail.com', 23, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'liamgevaerts@gmail.com', 24, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'ricardovandeworp@gmail.com', 8, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'ricardovandeworp@gmail.com', 9, 67)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'thijsvanrijsbergen@gmail.com', 21, 5)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'thijsvanrijsbergen@gmail.com', 22, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'thijsvanrijsbergen@gmail.com', 23, 63.65)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'thijsvanrijsbergen@gmail.com', 24, 0)
GO
INSERT [dbo].[Student] ([EmailAddress], [Name], [DateOfBirth], [Gender], [City], [Address], [Country], [PostalCode]) VALUES (N'liamgevaerts@gmail.com', N'Liam Gevaerts', CAST(N'1999-07-11' AS Date), N'Male', N'Rotterdam', N'Wokkenende 43', N'Netherlands', N'7542 GR')
INSERT [dbo].[Student] ([EmailAddress], [Name], [DateOfBirth], [Gender], [City], [Address], [Country], [PostalCode]) VALUES (N'ricardovandeworp@gmail.com', N'Ricardo van de Worp', CAST(N'1999-09-29' AS Date), N'Male', N'Middelburg', N'Janpieterstraat 178', N'Netherlands', N'8537 ET')
INSERT [dbo].[Student] ([EmailAddress], [Name], [DateOfBirth], [Gender], [City], [Address], [Country], [PostalCode]) VALUES (N'rietjehuizen@hotmail.com', N'Rietje Huizen', CAST(N'1980-04-07' AS Date), N'Female', N'Amsterdam', N'Zanddijkstraat 78', N'Netherlands', N'3786 ZT')
INSERT [dbo].[Student] ([EmailAddress], [Name], [DateOfBirth], [Gender], [City], [Address], [Country], [PostalCode]) VALUES (N'thijsvanrijsbergen@gmail.com', N'Thijs van Rijsbergen', CAST(N'2000-08-05' AS Date), N'Male', N'Breda', N'Hogeschoollaan 34', N'Netherlands', N'4820 GV')
GO
INSERT [dbo].[Webcast] ([Title], [Duration], [Description], [LecturerName], [LecturerOrg], [ContentItemId], [ViewCount]) VALUES (N'Advanced Front-End', 300, N'Importance of Front-End development', N'Gerard', N'Yale', 7, 546)
INSERT [dbo].[Webcast] ([Title], [Duration], [Description], [LecturerName], [LecturerOrg], [ContentItemId], [ViewCount]) VALUES (N'CSS course 1', 420, N'CSS course for Avans students', N'Erik', N'Avans', 12, 462)
INSERT [dbo].[Webcast] ([Title], [Duration], [Description], [LecturerName], [LecturerOrg], [ContentItemId], [ViewCount]) VALUES (N'Java course Part 1', 346, N'Java course Part 1', N'Ruud', N'Avans', 18, 221)
INSERT [dbo].[Webcast] ([Title], [Duration], [Description], [LecturerName], [LecturerOrg], [ContentItemId], [ViewCount]) VALUES (N'Java course Part 2', 547, N'Java course Part 2', N'Ruud', N'Avans', 19, 468)
INSERT [dbo].[Webcast] ([Title], [Duration], [Description], [LecturerName], [LecturerOrg], [ContentItemId], [ViewCount]) VALUES (N'Java course Part 3', 700, N'Java course Part 3', N'Ruud', N'Avans', 20, 933)
INSERT [dbo].[Webcast] ([Title], [Duration], [Description], [LecturerName], [LecturerOrg], [ContentItemId], [ViewCount]) VALUES (N'Python the basics', 100, N'CSS basics', N'Gerard', N'Yale', 3, 400)
INSERT [dbo].[Webcast] ([Title], [Duration], [Description], [LecturerName], [LecturerOrg], [ContentItemId], [ViewCount]) VALUES (N'The basics of creating scripts', 180, N'Scripts with Python', N'Gerard', N'Yale', 4, 100)
GO
ALTER TABLE [dbo].[Webcast] ADD  CONSTRAINT [DF_Webcast_ViewCount]  DEFAULT ((0)) FOR [ViewCount]
GO
ALTER TABLE [dbo].[Content]  WITH CHECK ADD  CONSTRAINT [FK_Content_Course] FOREIGN KEY([CourseName])
REFERENCES [dbo].[Course] ([CourseName])
GO
ALTER TABLE [dbo].[Content] CHECK CONSTRAINT [FK_Content_Course]
GO
ALTER TABLE [dbo].[Enrollment]  WITH CHECK ADD  CONSTRAINT [FK_Enrollment_Course] FOREIGN KEY([CourseName])
REFERENCES [dbo].[Course] ([CourseName])
GO
ALTER TABLE [dbo].[Enrollment] CHECK CONSTRAINT [FK_Enrollment_Course]
GO
ALTER TABLE [dbo].[Enrollment]  WITH CHECK ADD  CONSTRAINT [FK_Enrollment_Student] FOREIGN KEY([EmailAddress])
REFERENCES [dbo].[Student] ([EmailAddress])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Enrollment] CHECK CONSTRAINT [FK_Enrollment_Student]
GO
ALTER TABLE [dbo].[InterestingCourse]  WITH CHECK ADD  CONSTRAINT [FK_InterestingCourse_Course] FOREIGN KEY([CourseName])
REFERENCES [dbo].[Course] ([CourseName])
GO
ALTER TABLE [dbo].[InterestingCourse] CHECK CONSTRAINT [FK_InterestingCourse_Course]
GO
ALTER TABLE [dbo].[InterestingCourse]  WITH CHECK ADD  CONSTRAINT [FK_InterestingCourse_Course1] FOREIGN KEY([InterestingCourseName])
REFERENCES [dbo].[Course] ([CourseName])
GO
ALTER TABLE [dbo].[InterestingCourse] CHECK CONSTRAINT [FK_InterestingCourse_Course1]
GO
ALTER TABLE [dbo].[Module]  WITH CHECK ADD  CONSTRAINT [FK_Module_Content] FOREIGN KEY([ContentItemId])
REFERENCES [dbo].[Content] ([ContentItemId])
GO
ALTER TABLE [dbo].[Module] CHECK CONSTRAINT [FK_Module_Content]
GO
ALTER TABLE [dbo].[Module]  WITH CHECK ADD  CONSTRAINT [FK_Module_Course] FOREIGN KEY([CourseName])
REFERENCES [dbo].[Course] ([CourseName])
GO
ALTER TABLE [dbo].[Module] CHECK CONSTRAINT [FK_Module_Course]
GO
ALTER TABLE [dbo].[Progress]  WITH CHECK ADD  CONSTRAINT [FK_Progress_Content] FOREIGN KEY([ContentItemId])
REFERENCES [dbo].[Content] ([ContentItemId])
GO
ALTER TABLE [dbo].[Progress] CHECK CONSTRAINT [FK_Progress_Content]
GO
ALTER TABLE [dbo].[Progress]  WITH CHECK ADD  CONSTRAINT [FK_Progress_Student] FOREIGN KEY([StudentEmailAddress])
REFERENCES [dbo].[Student] ([EmailAddress])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Progress] CHECK CONSTRAINT [FK_Progress_Student]
GO
ALTER TABLE [dbo].[Webcast]  WITH CHECK ADD  CONSTRAINT [FK_Webcast_Content] FOREIGN KEY([ContentItemId])
REFERENCES [dbo].[Content] ([ContentItemId])
GO
ALTER TABLE [dbo].[Webcast] CHECK CONSTRAINT [FK_Webcast_Content]
GO
USE [master]
GO
ALTER DATABASE [Codecademy] SET  READ_WRITE 
GO
