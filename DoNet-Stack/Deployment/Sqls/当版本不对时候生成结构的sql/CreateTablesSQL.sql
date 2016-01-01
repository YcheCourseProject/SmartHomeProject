/*
Navicat SQL Server Data Transfer

Source Server         : SmartHomeDB
Source Server Version : 100000
Source Host           : 192.168.1.151:1433
Source Database       : SmartHomeDB
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 100000
File Encoding         : 65001

Date: 2015-03-19 15:52:19
*/


-- ----------------------------
-- Table structure for UserfulMeterData
-- ----------------------------
DROP TABLE [dbo].[UserfulMeterData]
GO
CREATE TABLE [dbo].[UserfulMeterData] (
[activePower] float(53) NULL ,
[reactivePower] float(53) NULL ,
[activeEnergy] float(53) NULL ,
[dateTime] varchar(50) NOT NULL 
)


GO

-- ----------------------------
-- Indexes structure for table UserfulMeterData
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table UserfulMeterData
-- ----------------------------
ALTER TABLE [dbo].[UserfulMeterData] ADD PRIMARY KEY ([dateTime])
GO
