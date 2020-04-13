<?php

namespace FirstBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Publication
 *
 * @ORM\Table(name="publication")
 * @ORM\Entity
 */
class Publication
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
     * @var string
     *
     * @ORM\Column(name="content", type="string", length=255, nullable=false)
     */
    private $content;

    /**
     * @var string
     *
     * @ORM\Column(name="class_name", type="string", length=250, nullable=true)
     */
    private $className = 'NULL';

    /**
     * @var integer
     *
     * @ORM\Column(name="teacher_id", type="integer", nullable=false)
     */
    private $teacherId;


}

