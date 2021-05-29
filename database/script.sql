-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 01 mai 2021 à 22:46
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP : 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestion_sutura`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

CREATE TABLE `administrateur` (
  `id` int(11) NOT NULL,
  `poste` varchar(255) NOT NULL,
  `photo` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `id_personne` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `administrateur`
--

INSERT INTO `administrateur` (`id`, `poste`, `photo`, `description`, `id_personne`) VALUES
(1, 'fdfd', 'https://www.photoweb.fr/espaces/magazine/wp-content/uploads/sites/3/2018/06/exemple-comment-ecrire-sur-une-photo.jpg', '', 4),
(2, 'fdfd', 'https://d1fmx1rbmqrxrr.cloudfront.net/cnet/i/edit/2020/05/applis-retouche-android-ios-big.jpg', '', 0);

-- --------------------------------------------------------

--
-- Structure de la table `caisse`
--

CREATE TABLE `caisse` (
  `id` int(11) NOT NULL,
  `is_favorable` tinyint(1) NOT NULL,
  `seuil` float NOT NULL,
  `montant_caisse` int(11) NOT NULL,
  `montant_actuel_caisse` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `cotisation`
--

CREATE TABLE `cotisation` (
  `id` int(11) NOT NULL,
  `montant` int(11) NOT NULL,
  `expiration` date NOT NULL,
  `statut` varchar(255) NOT NULL,
  `justificatif` varchar(255) NOT NULL,
  `validation` varchar(255) NOT NULL,
  `periode` int(11) NOT NULL,
  `id_caisse` int(11) NOT NULL,
  `id_etudiant` int(11) NOT NULL,
  `approved` bit(1) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `cotisation`
--

INSERT INTO `cotisation` (`id`, `montant`, `expiration`, `statut`, `justificatif`, `validation`, `periode`, `id_caisse`, `id_etudiant`, `approved`, `date`) VALUES
(4, 0, '2000-04-07', '', '', 'lk', 0, 0, 0, b'0', '1998-06-07 00:00:00'),
(5, 0, '1998-06-07', '', '', '', 0, 0, 0, b'0', '1998-06-07 00:00:00'),
(6, 0, '1998-06-07', '', '', '', 0, 0, 0, b'0', '1998-06-07 00:00:00'),
(7, 0, '1998-06-07', '', '', '', 0, 0, 0, b'0', '1998-06-07 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `depense`
--

CREATE TABLE `depense` (
  `id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `montant` double NOT NULL,
  `nature` varchar(255) NOT NULL,
  `id_justificatif` int(11) NOT NULL,
  `id_caisse` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `id_admin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `depense`
--

INSERT INTO `depense` (`id`, `date`, `montant`, `nature`, `id_justificatif`, `id_caisse`, `description`, `id_admin`) VALUES
(2, '1999-06-07 00:00:00', 0, '', 0, 0, '', 0),
(3, '1999-06-07 00:00:00', 0, '', 0, 0, '', 0),
(4, '1998-06-07 00:00:00', 0, '1998-06-07', 0, 0, '', 0);

-- --------------------------------------------------------

--
-- Structure de la table `don`
--

CREATE TABLE `don` (
  `id` int(11) NOT NULL,
  `donateur` varchar(255) NOT NULL,
  `montant` int(11) NOT NULL,
  `id_caisse` int(11) NOT NULL,
  `bienfaiteur` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `id_admin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `don`
--

INSERT INTO `don` (`id`, `donateur`, `montant`, `id_caisse`, `bienfaiteur`, `date`, `id_admin`) VALUES
(1, '', 0, 0, '44', '1998-06-07 00:00:00', 0),
(2, '', 0, 0, '11', '1998-06-07 00:00:00', 0),
(3, '55', 0, 5, '11', '1998-06-07 00:00:00', 0),
(4, '55', 0, 0, '11', '1998-06-07 00:00:00', 0),
(5, '55', 0, 0, '11', '1998-06-07 00:00:00', 0),
(6, '55', 0, 0, '11', '1998-06-07 00:00:00', 0),
(7, '', 0, 0, '', '1998-06-07 00:00:00', 0);

-- --------------------------------------------------------

--
-- Structure de la table `donateur`
--

CREATE TABLE `donateur` (
  `id` int(11) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `id` int(11) NOT NULL,
  `ville_etude` varchar(255) NOT NULL,
  `filiere` varchar(255) NOT NULL,
  `sexe` varchar(255) NOT NULL,
  `pays_origine` varchar(255) NOT NULL,
  `nombre_annee_au_maroc` int(11) NOT NULL,
  `date_inscription` datetime NOT NULL,
  `numero_telephone` int(11) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `id_personne` int(11) NOT NULL,
  `etablissement` varchar(255) DEFAULT NULL,
  `modification_tableau_de_bord` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`id`, `ville_etude`, `filiere`, `sexe`, `pays_origine`, `nombre_annee_au_maroc`, `date_inscription`, `numero_telephone`, `mot_de_passe`, `id_personne`, `etablissement`, `modification_tableau_de_bord`) VALUES
(1, '', '', '', '', 0, '1998-06-07 00:00:00', 0, '', 0, '', '1998-06-07'),
(2, '', '', '', '', 0, '1998-06-07 00:00:00', 0, '', 0, '', '');

-- --------------------------------------------------------

--
-- Structure de la table `justificatif`
--

CREATE TABLE `justificatif` (
  `id` int(11) NOT NULL,
  `piece_identite` varchar(255) NOT NULL,
  `document_justificatif` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE `personne` (
  `id` int(11) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `pret`
--

CREATE TABLE `pret` (
  `id` int(11) NOT NULL,
  `date_demande` datetime NOT NULL,
  `echeance` datetime NOT NULL DEFAULT current_timestamp(),
  `periode` int(11) NOT NULL,
  `montant` float NOT NULL,
  `objet` varchar(255) NOT NULL DEFAULT 'default',
  `commentaire` varchar(255) NOT NULL,
  `rib` int(11) NOT NULL,
  `statut_traitement` tinyint(1) NOT NULL DEFAULT 0,
  `etat` varchar(255) NOT NULL,
  `priorite` varchar(11) NOT NULL,
  `etat_remboursement` tinyint(1) NOT NULL,
  `date_traitement` datetime DEFAULT NULL,
  `id_justificatif` int(11) NOT NULL,
  `justificatif_pret_accorde` varchar(255) NOT NULL,
  `id_caisse` int(11) NOT NULL,
  `id_administrateur` int(11) NOT NULL,
  `id_etudiant` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `modification` datetime DEFAULT NULL,
  `raison` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `pret`
--

INSERT INTO `pret` (`id`, `date_demande`, `echeance`, `periode`, `montant`, `objet`, `commentaire`, `rib`, `statut_traitement`, `etat`, `priorite`, `etat_remboursement`, `date_traitement`, `id_justificatif`, `justificatif_pret_accorde`, `id_caisse`, `id_administrateur`, `id_etudiant`, `date`, `modification`, `raison`) VALUES
(3, '1999-04-05 00:00:00', '2000-06-07 00:00:00', 0, 0, '', '', 0, 0, 'true', '0.0', 0, NULL, 0, '', 0, 0, 0, '1998-06-07 00:00:00', '1999-06-07 00:00:00', ''),
(4, '1998-06-07 00:00:00', '1998-06-07 00:00:00', 0, 0, '', '', 0, 0, 'true', '0.0', 0, NULL, 0, '', 0, 0, 0, '1998-06-07 00:00:00', '1998-06-07 00:00:00', '');

-- --------------------------------------------------------

--
-- Structure de la table `remboursement`
--

CREATE TABLE `remboursement` (
  `id` int(11) NOT NULL,
  `montant` float NOT NULL,
  `date` datetime NOT NULL,
  `nombre_de_tranches` int(11) NOT NULL,
  `justificatif` varchar(255) NOT NULL,
  `statut` varchar(255) NOT NULL,
  `periode` int(11) NOT NULL,
  `id_caisse` int(11) NOT NULL,
  `id_pret` int(11) NOT NULL,
  `id_etudiant` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `remboursement`
--

INSERT INTO `remboursement` (`id`, `montant`, `date`, `nombre_de_tranches`, `justificatif`, `statut`, `periode`, `id_caisse`, `id_pret`, `id_etudiant`, `type`) VALUES
(1, 0, '1998-06-07 00:00:00', 7, '44', '0', 0, 5, 5, 0, 'jj'),
(3, 0, '2000-04-07 00:00:00', 7, '44', '0', 0, 5, 5, 0, 'jj'),
(4, 0, '1998-06-07 00:00:00', 0, '', '0', 0, 0, 0, 0, ''),
(5, 0, '1998-06-07 00:00:00', 0, '', '0', 0, 0, 0, 0, '');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `caisse`
--
ALTER TABLE `caisse`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `cotisation`
--
ALTER TABLE `cotisation`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `depense`
--
ALTER TABLE `depense`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `don`
--
ALTER TABLE `don`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `donateur`
--
ALTER TABLE `donateur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `justificatif`
--
ALTER TABLE `justificatif`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `pret`
--
ALTER TABLE `pret`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `remboursement`
--
ALTER TABLE `remboursement`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `administrateur`
--
ALTER TABLE `administrateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `caisse`
--
ALTER TABLE `caisse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `cotisation`
--
ALTER TABLE `cotisation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `depense`
--
ALTER TABLE `depense`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `don`
--
ALTER TABLE `don`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `donateur`
--
ALTER TABLE `donateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `justificatif`
--
ALTER TABLE `justificatif`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `personne`
--
ALTER TABLE `personne`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `pret`
--
ALTER TABLE `pret`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `remboursement`
--
ALTER TABLE `remboursement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
