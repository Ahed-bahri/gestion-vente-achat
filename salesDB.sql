-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mer. 17 fév. 2021 à 15:19
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP : 7.2.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `salesDB`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `registrationClient` varchar(35) NOT NULL,
  `fullNameClient` varchar(35) NOT NULL,
  `regionClient` varchar(35) NOT NULL,
  `phoneClient` int(40) NOT NULL,
  `emailClient` varchar(35) NOT NULL,
  `bankNameClient` varchar(35) NOT NULL,
  `agencyNameClient` varchar(35) NOT NULL,
  `ribClient` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`registrationClient`, `fullNameClient`, `regionClient`, `phoneClient`, `emailClient`, `bankNameClient`, `agencyNameClient`, `ribClient`) VALUES
('18FGHJ', 'ghjjhgj', 'Tunis', 4522656, 'aza@dfdf.fd', 'ATB', 'ATB SSS', 556566),
('18IFLHZS', 'Asma Ben Amor', 'Mahdia', 9765412, 'asma.amor@poly.tn', 'QNB', 'QNB Mahdia', 756402564),
('19IFJ076', 'Ahed Bahri', 'Sousse', 21548652, 'ahedBahri@gmail.com', 'ATB', 'ATB Jawhra', 201545236),
('19IFJ458', 'Manel Ben Salem', 'Mahdia', 5421564, 'manel20@gmail.com', 'BNA', 'BNA Chebba', 540487545),
('19IFJKHJ', 'Raoua Hajri', 'Bizerte', 20344541, 'raoua@hotmail.fr', 'TSB', 'TSB Bizerte', 488956423);

-- --------------------------------------------------------

--
-- Structure de la table `companyMoral`
--

CREATE TABLE `companyMoral` (
  `registrationMoral` varchar(35) NOT NULL,
  `fullNameMoral` varchar(35) NOT NULL,
  `regionMoral` varchar(35) NOT NULL,
  `phoneMoral` int(40) NOT NULL,
  `emailMoral` varchar(35) NOT NULL,
  `bankNameMoral` varchar(35) NOT NULL,
  `agencyNameMoral` varchar(35) NOT NULL,
  `ribMoral` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `companyMoral`
--

INSERT INTO `companyMoral` (`registrationMoral`, `fullNameMoral`, `regionMoral`, `phoneMoral`, `emailMoral`, `bankNameMoral`, `agencyNameMoral`, `ribMoral`) VALUES
('4560ML', 'Happy Store', 'Sousse', 45455405, 'happ@email.tn', 'ATB', 'ATB Sousse', 451210013),
('474ML', 'Naceur Store', 'Bizerte', 41212105, 'naseurStore@yahoo.fr', 'BNA', 'BNA Bizerte', 634563655),
('487ML', 'Abida Store', 'Sousse', 97854120, 'abida@store.tn', 'BIAT', 'BIAT Sousse', 12032158),
('541ML', 'AlBARKA', 'Sousse', 5514541, 'barka@gmail.com', 'BT', 'BT Sousse', 345356353);

-- --------------------------------------------------------

--
-- Structure de la table `companyPhysical`
--

CREATE TABLE `companyPhysical` (
  `registrationPhysical` varchar(35) NOT NULL,
  `fullNamePhysical` varchar(35) NOT NULL,
  `regionPhysical` varchar(35) NOT NULL,
  `phonePhysical` int(40) NOT NULL,
  `emailPhysical` varchar(35) NOT NULL,
  `bankNamePhysical` varchar(35) NOT NULL,
  `agencyNamePhysical` varchar(35) NOT NULL,
  `ribPhysical` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `companyPhysical`
--

INSERT INTO `companyPhysical` (`registrationPhysical`, `fullNamePhysical`, `regionPhysical`, `phonePhysical`, `emailPhysical`, `bankNamePhysical`, `agencyNamePhysical`, `ribPhysical`) VALUES
('01259PH', 'City Market', 'Sfax', 97511251, 'city@yahoo.fr', 'STB', 'STB Sfax', 564120654),
('4096PH', 'Magasin Generale', 'Mahdia', 78912049, 'mg@google.tn', 'TSB', 'TSB Mahdia', 489871230),
('4512PH', 'Carrefour ', 'Monastir', 456456564, 'carrefour@tunisie.tn', 'BIAT', 'BIAT Monastir', 45656445);

-- --------------------------------------------------------

--
-- Structure de la table `connect`
--

CREATE TABLE `connect` (
  `login` varchar(20) NOT NULL,
  `password` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `connect`
--

INSERT INTO `connect` (`login`, `password`) VALUES
('ahed', 2610),
('bahri', 2020);

-- --------------------------------------------------------

--
-- Structure de la table `Products`
--

CREATE TABLE `Products` (
  `refStock` int(11) NOT NULL,
  `designation` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `unite` varchar(50) NOT NULL,
  `ProviderName` varchar(40) NOT NULL,
  `quantite` int(11) NOT NULL,
  `quantiteVente` int(11) NOT NULL,
  `quantiteReste` int(11) NOT NULL,
  `prixUnitaire` float NOT NULL,
  `prixVente` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `Products`
--

INSERT INTO `Products` (`refStock`, `designation`, `category`, `unite`, `ProviderName`, `quantite`, `quantiteVente`, `quantiteReste`, `prixUnitaire`, `prixVente`) VALUES
(1, 'Boissons', 'Jus', 'L', 'Amir Ezzine', 30, 9, 21, 1200, 36000),
(2, 'Cake', 'Fiançailles', 'Piece', 'Borhen Chahed', 35, 13, 22, 1200, 36000),
(3, 'Cereales', 'sans gluten', 'Kg', 'Kamilia Abderahman', 28, 9, 19, 840, 23520),
(4, 'Viandes', 'beuf', 'Kg', 'Amir Ezzine', 50, 25, 25, 20800, 1040000),
(5, 'Legumes', 'Potatos', 'Kg', 'Med Ezzedine', 24, 7, 17, 750, 18000);

-- --------------------------------------------------------

--
-- Structure de la table `provider`
--

CREATE TABLE `provider` (
  `registration` varchar(35) NOT NULL,
  `fullName` varchar(35) NOT NULL,
  `region` varchar(35) NOT NULL,
  `phone` int(11) NOT NULL,
  `email` varchar(35) NOT NULL,
  `bankName` varchar(35) NOT NULL,
  `agencyName` varchar(35) NOT NULL,
  `rib` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `provider`
--

INSERT INTO `provider` (`registration`, `fullName`, `region`, `phone`, `email`, `bankName`, `agencyName`, `rib`) VALUES
('0245DSZ', 'Kouloud Abuli', 'Sousse', 97456546, 'khouloud@hormail.fr', 'ATB', 'ATB Sousse', 454554546),
('13HLSN', 'Borhen Chahed', 'Monastir', 26145201, 'bibou@gmail.com', 'STB', 'STB Jamel', 231056444),
('18IFJ20', 'Med Ezzedine', 'Tunis', 54120980, 'mohamed_Ezz@yahoo.fr', 'BIAT', 'BIAT Sahloul', 21054880),
('45GFD', 'Amir Ezzine', 'Tunis', 98156412, 'amir48@yahoo.fr', 'BIAT', 'BIAT Tunis', 645456054),
('940HSU', 'Kamilia Abderahman', 'Sfax', 21456645, 'kimou@edu.tn', 'STB', 'STB Sfax', 345534543),
('98GSD', 'Sohan Ayoub', 'Bizerte', 98716345, 'Sohan@outlook.com', 'BFT', 'BFT Bizerte', 451512123);

-- --------------------------------------------------------

--
-- Structure de la table `vente`
--

CREATE TABLE `vente` (
  `idVente` int(11) NOT NULL,
  `nomClient` varchar(60) NOT NULL,
  `details` text NOT NULL,
  `prix` float NOT NULL,
  `dateVente` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `vente`
--

INSERT INTO `vente` (`idVente`, `nomClient`, `details`, `prix`, `dateVente`) VALUES
(1, 'Asma Ben Amor', 'N° 1 Designation: Pepsi Paid : 18400.0 Quantity : 1\r\nN° 2 Designation: Drink Paid: 18400.0 Quantity : 1\r\n', 36800, '2020-12-21 21:50:17'),
(7, 'Manel Ben Salem', 'N° 1 Designation : Cake Paid : 36000.0 Quantity : 1\nN° 2 Designation : Legumes Paid : 18000.0 Quantity : 3\n', 90000, '2021-01-25 14:08:25');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`registrationClient`);

--
-- Index pour la table `companyMoral`
--
ALTER TABLE `companyMoral`
  ADD PRIMARY KEY (`registrationMoral`);

--
-- Index pour la table `companyPhysical`
--
ALTER TABLE `companyPhysical`
  ADD PRIMARY KEY (`registrationPhysical`);

--
-- Index pour la table `Products`
--
ALTER TABLE `Products`
  ADD PRIMARY KEY (`refStock`);

--
-- Index pour la table `provider`
--
ALTER TABLE `provider`
  ADD PRIMARY KEY (`registration`);

--
-- Index pour la table `vente`
--
ALTER TABLE `vente`
  ADD PRIMARY KEY (`idVente`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `vente`
--
ALTER TABLE `vente`
  MODIFY `idVente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
