USE [EFACTORY-SCAFFOLDING]
GO

/****** Object:  Table [sch_myApp_APPL].[Book]    Script Date: 17/06/2015 11:25:43 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE SCHEMA sch_myApp_APPL

CREATE TABLE [sch_myApp_APPL].[Book](
	[id] 	[INT] NOT NULL IDENTITY(0, 1) PRIMARY KEY, 
	[title] [nvarchar](100) NULL,
	[description] [nvarchar](4000) NULL
)

GO


