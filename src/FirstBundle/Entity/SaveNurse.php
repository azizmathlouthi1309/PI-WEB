<?php

namespace FirstBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * SaveNurse
 *
 * @ORM\Table(name="save_nurse")
 * @ORM\Entity
 */
class SaveNurse
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
     * @ORM\Column(name="date_begin", type="string", length=10, nullable=false)
     */
    private $dateBegin;

    /**
     * @var string
     *
     * @ORM\Column(name="date_end", type="string", length=10, nullable=false)
     */
    private $dateEnd;


}

