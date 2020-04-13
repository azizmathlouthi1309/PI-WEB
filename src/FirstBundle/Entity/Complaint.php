<?php

namespace FirstBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Complaint
 *
 * @ORM\Table(name="complaint")
 * @ORM\Entity
 */
class Complaint
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="subject", type="string", length=255, nullable=false)
     */
    private $subject;

    /**
     * @var integer
     *
     * @ORM\Column(name="teacher_id", type="integer", nullable=true)
     */
    private $teacherId = 'NULL';

    /**
     * @var integer
     *
     * @ORM\Column(name="parent_id", type="integer", nullable=true)
     */
    private $parentId = 'NULL';

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=255, nullable=true)
     */
    private $type = 'NULL';

    /**
     * @var string
     *
     * @ORM\Column(name="message", type="string", length=255, nullable=false)
     */
    private $message;

    /**
     * @var string
     *
     * @ORM\Column(name="response", type="string", length=255, nullable=true)
     */
    private $response = 'NULL';

    /**
     * @var integer
     *
     * @ORM\Column(name="status", type="integer", nullable=true)
     */
    private $status = '0';


}

