DELIMITER //

DROP PROCEDURE IF EXISTS set_sequence_cities;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_sequence_cities`()
BEGIN
    DECLARE current_id INT DEFAULT 1;

    DECLARE done INT DEFAULT FALSE;

    DECLARE city_cursor CURSOR FOR
        SELECT id
        FROM cities;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN city_cursor;

    read_loop: LOOP
        FETCH city_cursor INTO current_id;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- Update cities table with sequential id
        UPDATE cities SET country_id = current_id WHERE id = current_id;
        SET current_id = current_id + 1;
    END LOOP;

    CLOSE city_cursor;
END//

DROP PROCEDURE IF EXISTS set_sequence_companies;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_sequence_companies`()
BEGIN
    DECLARE current_id INT DEFAULT 1;

    DECLARE done INT DEFAULT FALSE;

    DECLARE company_cursor CURSOR FOR
        SELECT id
        FROM companies;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN company_cursor;

    read_loop: LOOP
        FETCH company_cursor INTO current_id;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- Update companies table with sequential id
        UPDATE companies SET street_id = current_id WHERE id = current_id;
        SET current_id = current_id + 1;
    END LOOP;

    CLOSE company_cursor;
END//

DROP PROCEDURE IF EXISTS set_sequence_invitations;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_sequence_invitations`()
BEGIN
    DECLARE current_id INT DEFAULT 1;

    DECLARE done INT DEFAULT FALSE;

    DECLARE invitation_cursor CURSOR FOR
        SELECT id
        FROM invitations;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN invitation_cursor;

    read_loop: LOOP
        FETCH invitation_cursor INTO current_id;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- Update invitations table with sequential id
        UPDATE invitations SET conversation_id = current_id WHERE id = current_id;
        UPDATE invitations SET invited_operator_id = current_id WHERE id = current_id;
        SET current_id = current_id + 1;
    END LOOP;

    CLOSE invitation_cursor;
END//

DROP PROCEDURE IF EXISTS set_sequence_messages;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_sequence_messages`()
BEGIN
    DECLARE current_id INT DEFAULT 1;

    DECLARE done INT DEFAULT FALSE;

    DECLARE message_cursor CURSOR FOR
        SELECT id
        FROM messages;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN message_cursor;

    read_loop: LOOP
        FETCH message_cursor INTO current_id;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- Update messages table with sequential id
        UPDATE messages SET conversation_id = current_id WHERE id = current_id;
        UPDATE messages SET receiver_id = current_id WHERE id = current_id;
        UPDATE messages SET sender_id = current_id WHERE id = current_id;
        SET current_id = current_id + 1;
    END LOOP;

    CLOSE message_cursor;
END//

DROP PROCEDURE IF EXISTS set_sequence_people;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_sequence_people`()
BEGIN
    DECLARE current_id INT DEFAULT 1;

    DECLARE done INT DEFAULT FALSE;

    DECLARE people_cursor CURSOR FOR
        SELECT id
        FROM people
        WHERE person_type = 'Operator';

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN people_cursor;

    read_loop: LOOP
        FETCH people_cursor INTO current_id;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- Update people table with sequential id for Operators
        UPDATE people SET company_id = current_id WHERE id = current_id;
        SET current_id = current_id + 1;
    END LOOP;

    CLOSE people_cursor;
END//

DROP PROCEDURE IF EXISTS set_sequence_streets;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_sequence_streets`()
BEGIN
    DECLARE current_id INT DEFAULT 1;

    DECLARE done INT DEFAULT FALSE;

    DECLARE street_cursor CURSOR FOR
        SELECT id
        FROM streets;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN street_cursor;

    read_loop: LOOP
        FETCH street_cursor INTO current_id;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- Update streets table with sequential id
        UPDATE streets SET city_id = current_id WHERE id = current_id;
        SET current_id = current_id + 1;
    END LOOP;

    CLOSE street_cursor;
END//

DELIMITER ;
